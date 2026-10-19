package com.itheima.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/requestservlet03")
public class RequestServlet03 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        //获取请求中的所有参数名称
        Enumeration<String> parameterNames = req.getParameterNames();
        System.out.println("请求中的所有参数名称：");
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            System.out.println(paramName);
        }
        //获取指定参数的值
        String username = req.getParameter("username");
        System.out.println("请求中参数username的值：" + username);
        //获取指定参数的值
        String[] sports = req.getParameterValues("sports");
        System.out.println("请求中参数sports的值：");
        for(String sport : sports){
            System.out.println(sport + " ");
        }
        //获取请求中所有请求参数的键值对
        Map<String, String[]> parameterMap = req.getParameterMap();
        System.out.println("所有请求参数的键值对集合：");
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String paramName = entry.getKey();
            String[] paramValues = entry.getValue();
            System.out.println("Name: " + paramName +
                    "，Values: " + Arrays.toString(paramValues));
        }
    }
}