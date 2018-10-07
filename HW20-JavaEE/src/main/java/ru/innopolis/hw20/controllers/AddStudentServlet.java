package ru.innopolis.hw20.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.hw20.service.StudentService;
import ru.innopolis.hw20.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStudentServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddStudentServlet.class);
    private final StudentService studentService = new StudentServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/WEB-INF/pages/addStudents.jsp").forward(req, resp);
        } catch (IOException | ServletException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name").trim();
        String surname = req.getParameter("surname").trim();
        String nameGroup = req.getParameter("nameGroup").trim();
        String age = req.getParameter("age");
        String contact = req.getParameter("contact").trim();
        try {
            if (checksDataForValidity(name, surname, nameGroup, age, contact)) {
                resp.sendRedirect("/inner/addStudents?action=notAllFieldsFilled");
            } else {
                if (studentService.addStudents(name, surname, nameGroup, age, contact)) {
                    resp.sendRedirect("/inner/addStudents");
                } else {
                    resp.sendRedirect("/inner/addStudents?action=studentExists");
                }
            }
        } catch (
                IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private boolean checksDataForValidity(String name, String surname, String nameGroup, String age, String contact) {
        return (name == null || name.length() == 0 || surname == null || surname.length() == 0 ||
                nameGroup == null || nameGroup.length() == 0 || age == null || age.length() == 0 ||
                contact == null || contact.length() == 0);
    }
}
