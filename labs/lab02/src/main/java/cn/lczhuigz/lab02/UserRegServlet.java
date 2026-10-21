package cn.lczhuigz.lab02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class UserRegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 接收数据
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        // 后台打印（在 IDEA 的控制台查看）
        System.out.println("====== 收到注册请求 ======");
        System.out.println("注册用户名: " + userName);
        System.out.println("注册密码: " + password);
        System.out.println("==========================");

        // 输出注册结果给浏览器
        PrintWriter out = response.getWriter();
        out.println("<h2 style='color:blue;text-align:center;'>注册成功！</h2>");
        out.println("<p style='text-align:center;'>已接收您的信息，请查看后台控制台打印的日志。</p>");
        out.println("<div style='text-align:center;'><a href='loginView'>去登录</a></div>");
    }
}