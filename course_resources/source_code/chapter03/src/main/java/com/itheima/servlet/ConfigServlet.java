package com.itheima.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(urlPatterns = "/configservlet",
        initParams = {@WebInitParam(name = "encoding",value = "UTF-8")})
public class ConfigServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        //获取ServletConfig对象
        ServletConfig config = this.getServletConfig();
        //获取参数名称为"encoding"的参数值
        String param = config.getInitParameter("encoding");
        pw.println("参数encoding的值为："+ param);
    }
}