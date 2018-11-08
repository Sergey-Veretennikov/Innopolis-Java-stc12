package ru.innopolis.hw20.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.hw20.pojo.Student;
import ru.innopolis.hw20.repository.dao.StudentDaoImpl;
import ru.innopolis.hw20.service.StudentService;
import ru.innopolis.hw20.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(StudentServlet.class);
    private final StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<Student> list = studentService.getAllStudents();
        req.setAttribute("list", list);
        LOGGER.info(list.toString());
        try {
            req.getRequestDispatcher("/WEB-INF/pages/students.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
