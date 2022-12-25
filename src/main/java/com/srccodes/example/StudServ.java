package com.srccodes.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.StudentRepository;
public class StudServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StudServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter()
            .append("Served at: ")
            .append(request.getContextPath());
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        String key = request.getParameter("id");
        if (key == null) {
            StudentRepository.GetAll(printWriter);
        } else {
            StudentRepository.GetOne(printWriter, key);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        StudentRepository.Create(printWriter, stud);
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
        StudentRepository.Update(stud, key);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        String key = request.getParameter("id");
        StudentRepository.Delete(key,printWriter);
    }
}
