package com.itheima.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;

@WebServlet("/requestservlet01")
public class RequestServlet01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse
            resp)  {
        System.out.println("请求方法：" + req.getMethod());
        System.out.println("请求URL：" +
                req.getRequestURL().toString());
        System.out.println("Servlet上下文的路径：" +
                req.getContextPath());
        System.out.println("URL中对应于Servlet的路径：" + req.getServletPath());
        System.out.println("请求URI：" + req.getRequestURI());
        System.out.println("请求的URL中的查询字符串：" + req.getQueryString());
        System.out.println("请求协议和版本号：" + req.getProtocol());
    }
}