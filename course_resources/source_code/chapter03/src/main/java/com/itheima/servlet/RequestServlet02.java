package com.itheima.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@WebServlet("/requestservlet02")
public class RequestServlet02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp) {
        String contentType = req.getContentType();
        String contentType2 = req.getHeader("Content-Type");
        System.out.println("getContentType()方法获取Content-Type字段的值："+contentType);
        System.out.println("getHeader()方法获取Content-Type字段的值："+contentType2);
        //获取所有请求头字段的名称和对应值
        System.out.println("所有请求头字段的名称和对应值：");
        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String s = headerNames.nextElement();
            System.out.println(s + "：" + req.getHeader(s));
        }
        System.out.println("---------------------------");
        //获取请求的字符编码
        String encoding = req.getCharacterEncoding();
        System.out.println("请求的字符编码：" + encoding);
    }
}