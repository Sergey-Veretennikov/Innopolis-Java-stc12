package ru.innopolis.hw20.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.hw20.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStudentServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddStudentServlet.class);
    private StudentServiceImpl studentService;

    @Override
    public void init() throws ServletException {
        super.init();
        studentService = new StudentServiceImpl();
    }

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
            if (name == null || name.length() == 0 ||
                    surname == null || surname.length() == 0 ||
                    nameGroup == null || nameGroup.length() == 0 ||
                    age == null || age.length() == 0 ||
                    contact == null || contact.length() == 0) {
                resp.sendRedirect("/inner/addStudents?action=notAllFieldsFilled");
            } else {
                studentService.AddStudents(name, surname, nameGroup, age, contact);
                if (studentService.getFlagStudentExists()) {
                    studentService.setFlagStudentExists(false);
                    resp.sendRedirect("/inner/addStudents?action=studentExists");
                } else {
                    resp.sendRedirect("/inner/addStudents");
                }
            }
        } catch (
                IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
