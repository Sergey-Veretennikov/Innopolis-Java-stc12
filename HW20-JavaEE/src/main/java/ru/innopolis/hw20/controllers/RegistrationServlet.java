package ru.innopolis.hw20.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.hw20.repository.dao.UserDaoImpl;
import ru.innopolis.hw20.service.UserService;
import ru.innopolis.hw20.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
    private static final String LOGIN = "login";
    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getSession().getAttribute(LOGIN) != null) {
                resp.sendRedirect("/inner/dashboard");
            } else {
                req.getRequestDispatcher("/registration.jsp").forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter(LOGIN).trim();
        String password = req.getParameter("password").trim();
        String role = req.getParameter("role");
        try {
            if (login == null || login.length() == 0 ||
                    password == null || password.length() == 0 || role == null) {
                resp.sendRedirect("/registration?action=notAllFieldsFilled");
            } else {
                if (userService.addUser(login, password, definesTheValueOfTheRole(role))) {
                    req.getSession().setAttribute(LOGIN, login);
                    req.getSession().setAttribute("role", role);
                    resp.sendRedirect("/inner/dashboard");
                } else {
                    resp.sendRedirect("/registration?action=studentExists");
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private int definesTheValueOfTheRole(String role) {
        if ("Teacher".equals(role)) {
            return 1;
        }
        return 2;
    }
}
