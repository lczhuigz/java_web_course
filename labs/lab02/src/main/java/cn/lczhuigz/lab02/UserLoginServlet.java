package cn.lczhuigz.lab02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 设置请求和响应的编码，防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 2. 获取表单提交的数据
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        // 3. 业务逻辑处理（判断账号密码）
        PrintWriter out = response.getWriter();
        if ("admin".equals(userName) && "123456".equals(password)) {
            out.println("<h2 style='color:green;text-align:center;'>登录成功！欢迎您，" + userName + "</h2>");
        } else {
            out.println("<h2 style='color:red;text-align:center;'>登录失败！用户名或密码错误。</h2>");
        }

        // 提供一个返回链接
        out.println("<div style='text-align:center;'><a href='loginView'>返回登录页</a></div>");
    }

    // 如果用户直接在浏览器地址栏敲 /login，会走 GET 请求，这里重定向到登录页
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("loginView");
    }
}