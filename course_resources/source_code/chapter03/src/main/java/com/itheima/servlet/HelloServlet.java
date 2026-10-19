package com.itheima.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
     @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        //获取输出流对象
        PrintWriter printWriter = resp.getWriter();
        //设置响应的数据
        printWriter.println("Hello Servlet");
    }
}