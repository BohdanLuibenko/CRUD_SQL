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
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public StudentServlet() {
        super();
    }
    private StudentService studentService = new StudentService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter()
            .append("Served at: ")
            .append(request.getContextPath());
        response.setContentType("text/html");
        PrintWriter p = response.getWriter();
        p.print("<h1>id name midlename lastname</h1>");
        String key = request.getParameter("id");
        if (key == null) {
            ArrayList<Student> students = new ArrayList<Student>();
            students = studentService.getAll();
            for (int i = 0; i < students.size(); i++)
                p.print("<h1>" + students.get(i).id + " " + students.get(i).name + " " + students.get(i).midlename + " "
                        + students.get(i).lastname + "</h1>");
        } else {
            Student student = new Student();
            student = studentService.getOne(key);
            p.print("<h1>" + student.id + " " + student.name + " " + student.midlename + " " + student.lastname
                    + "</h1>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter p = response.getWriter();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
        StringBuilder res = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
            res.append(responseLine.trim());
        }
        String jsonstr = res.toString();
        Gson g = new Gson();
        Student student = g.fromJson(jsonstr, Student.class);
        p.print("<h1>Created:</h1>");
        p.print("<h1>id name midlename lastname</h1>");
        student = studentService.Create(student);
        p.print("<h1>" + student.id + " " + student.name + " " + student.midlename + " " + student.lastname + "</h1>");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key = request.getParameter("id");
        PrintWriter printWriter = response.getWriter();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
        StringBuilder res = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
            res.append(responseLine.trim());
        }
        System.out.println(res.toString());
        String jsonstr = res.toString();
        Gson g = new Gson();
        Student stud = g.fromJson(jsonstr, Student.class);
        studentService.Update(stud, key);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        String key = request.getParameter("id");
        studentService.Delete(key, printWriter);
    }
}
