package com.itheima.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/forwardservlet02")
public class ForwardServlet02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        //获取请求参数
        String username = (String) req.getAttribute("username");
        //输出响应
        PrintWriter pw = resp.getWriter();
        if (username != null){
            pw.println("username属性的值：" + username);
        }
    }
}