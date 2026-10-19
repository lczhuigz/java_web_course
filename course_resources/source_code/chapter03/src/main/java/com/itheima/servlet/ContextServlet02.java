package com.itheima.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
@WebServlet("/contextservlet02")
public class ContextServlet02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        ServletContext context = this.getServletContext();
        //根据指定相对路径获取资源文件输入流
        InputStream in = context.getResourceAsStream("/WEB-INF/classes/db.properties");
        //创建Properties对象
        Properties prop = new Properties();
        //封装输入流为Properties对象
        prop.load(in);
        //输出Properties对象中封装的数据到响应
        PrintWriter pw = resp.getWriter();
        pw.println("username的值："+prop.getProperty("username") +
                "<br>");
        pw.println("userpassword的值："+prop.getProperty("userpassword"));
    }
}