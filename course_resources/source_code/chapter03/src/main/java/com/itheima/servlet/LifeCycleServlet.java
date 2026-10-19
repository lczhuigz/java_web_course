package com.itheima.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/lifecycle")
public class LifeCycleServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config)  {
        System.out.println("执行init()方法");
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse
            resp)  {
        System.out.println("执行service()方法，开始处理请求");
    }
    @Override
    public void destroy() {
        System.out.println("执行destroy()方法");
    }
}