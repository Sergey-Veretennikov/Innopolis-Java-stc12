package ru.innopolis.hw20.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.hw20.pojo.Student;
import ru.innopolis.hw20.service.StudentService;
import ru.innopolis.hw20.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getRootLogger();
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        super.init();
        studentService = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<Student> list = studentService.getAllStudents();
        req.setAttribute("list", list);
        LOGGER.info(list.toString());
        try {
            req.getRequestDispatcher("/students.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
