package ru.innopolis.hw20.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.hw20.pojo.User;
import ru.innopolis.hw20.repository.dao.UserDaoImpl;
import ru.innopolis.hw20.service.UserService;
import ru.innopolis.hw20.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private static final String LOGIN = "login";
    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getSession().getAttribute(LOGIN) != null) {
                resp.sendRedirect("/inner/dashboard");
            } else {
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (IOException | ServletException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter("password");
        User user = userService.checkAuth(login, password);
        try {
            if (user != null) {
                int role = user.getRole();
                req.getSession().setAttribute(LOGIN, login);
                req.getSession().setAttribute("role", role);
                resp.sendRedirect("/inner/dashboard");
            } else {
                resp.sendRedirect("/login?action=wrongUser");
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
