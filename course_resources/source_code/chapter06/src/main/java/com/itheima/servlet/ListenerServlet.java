package com.itheima.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/listener")
public class ListenerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse
            resp) {
        System.out.println("测试监听器");
        //获取HttpSession对象
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(10);
    }
}