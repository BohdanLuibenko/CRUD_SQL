package com.srccodes.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import util.HttpUtil;

public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private StudentService studentService = new StudentService();
    private HttpUtil httputil = new HttpUtil();
    
    public StudentServlet() {

        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter p = response.getWriter();

        response.setContentType("text/html");
        p.print("<h1>id name midlename lastname</h1>");
        String key = request.getParameter("id");
        if (key == null) {
            ArrayList<Student> students = studentService.getAll();
            for (int i = 0; i < students.size(); i++) {
                p.print("<h1>" + students.get(i).Id + " " + students.get(i).Name + " " + students.get(i).midleName + " "
                        + students.get(i).lastName + "</h1>");
            }
        } else {
            Student student = studentService.getOne(key);
            p.print("<h1>" + student.Id + " " + student.Name + " " + student.midleName + " " + student.lastName
                    + "</h1>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter p = response.getWriter();
  
        Student student = httputil.convertToStudent(request);
        p.print("<h1>Created:</h1>");
        p.print("<h1>id name midlename lastname</h1>");
        student = studentService.create(student);
        p.print("<h1>" + student.Id + " " + student.Name + " " + student.midleName + " " + student.lastName + "</h1>");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String key = request.getParameter("id");
        Student student = httputil.convertToStudent(request);
        studentService.update(student, key);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String key = request.getParameter("id");
        studentService.delete(key);
    }
}
