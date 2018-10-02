package ru.innopolis.hw20.controllers;

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
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        super.init();
        studentService = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> list = studentService.getAllStudents();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/students.jsp").forward(req, resp);
    }
}
