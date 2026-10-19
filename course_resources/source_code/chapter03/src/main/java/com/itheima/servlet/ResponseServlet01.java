package com.itheima.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/responseservlet01")
public class ResponseServlet01 extends HttpServlet {
    @Override
        protected void service(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        // 设置内容类型和字符编码
        resp.setContentType("text/html;charset=UTF-8");
        // 设置一个整数类型的响应头
        resp.setIntHeader("Refresh", 20); // 每20秒刷新一次页面
        // 设置一个缓存控制响应头，设置为不缓存
        resp.setHeader("Cache-Control", 
                "no-cache, no-store, must-revalidate");
        // 添加两个额外的响应头
        resp.addHeader("X-Custom-Header", "Version 1.0");
        resp.addHeader("X-Custom-Header", "Admin");
        //输出响应内容
        PrintWriter pw = resp.getWriter();
        pw.println("<p>2024年3月20日，鹊桥二号中继星成功发射</p>");
    }
}