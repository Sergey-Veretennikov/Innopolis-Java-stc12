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
    private static final Logger LOGGER = Logger.getRootLogger();
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        super.init();
        studentService = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addStudents.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String nameGroup = req.getParameter("nameGroup");
        String age = req.getParameter("age");
        String contact = req.getParameter("contact");
        if (name == null || name.trim().length() == 0 ||
                surname == null || surname.trim().length() == 0 ||
                nameGroup == null || nameGroup.trim().length() == 0 ||
                age == null || age.trim().length() == 0) {
            resp.sendRedirect("/addStudents?action=notAllFieldsFilled");
        } else {
            studentService.AddStudents(name, surname, nameGroup, age, contact);
        }
    }
}
