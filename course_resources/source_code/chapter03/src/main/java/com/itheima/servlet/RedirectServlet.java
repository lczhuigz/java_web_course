package com.itheima.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/redirectservlet")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp) throws  IOException {
        resp.setContentType("text/html;charset=UTF-8");
        //获取用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //假设正确的用户名和密码为admin和123456
        if(username.equals("admin") &&  password.equals("123456")){
            //用户名和密码正确，重定向到登录成功欢迎页面
            resp.sendRedirect("welcome.html");
        }else{
            //用户名和密码错误，重定向到登录页面，重新登录
            resp.sendRedirect("login.html");
        }
    }
}