package com.itheima.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
@WebServlet("/myServlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse
            resp) throws IOException {

        PrintWriter pw = resp.getWriter();
        pw.println("Hello MyServlet");
    }
}