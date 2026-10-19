package com.itheima.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/forwardservlet01")
public class ForwardServlet01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        //设置请求属性
        req.setAttribute("username","lisi");
        //获取ResquestDispatcher对象
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/forwardservlet02");
        //调用forward()方法转发请求
        dispatcher.forward(req,resp);
    }
}