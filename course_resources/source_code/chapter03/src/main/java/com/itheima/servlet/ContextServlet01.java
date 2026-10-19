package com.itheima.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/contextservlet01")
public class ContextServlet01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        //获取ServletContext对象
        ServletContext context = this.getServletContext();
        //获取参数databaseURL的值
        String param = context.getInitParameter("databaseURL");
        pw.println("数据库连接URL："+ param);
    }
}