package com.itheima.task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 登录Servlet，处理用户登录请求
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
     * 处理POST请求的登录逻辑
     *
     * @param request  包含登录参数的HttpServletRequest对象
     * @param response 用于输出响应的HttpServletResponse对象
     * @throws ServletException 如果Servlet遇到异常
     * @throws IOException      如果发生输入或输出异常
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应的字符编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取用户名和密码参数
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        // 检查用户名和密码是否为空
        if (userName == null || password == null || userName.isEmpty() || password.isEmpty()) {
            response.getWriter().println("用户名和密码不能为空!");
            return;
        }

        // 从ServletContext中获取用户映射
        Map<String, User> userMap = (Map<String, User>) getServletContext().getAttribute("userMap");
        // 检查用户映射是否存在，并验证用户名和密码
        if (userMap != null && userMap.containsKey(userName)) {
            User user = userMap.get(userName);
            if (user.getPassword().equals(password)) {
                response.getWriter().println("登录成功!");
            } else {
                response.getWriter().println("密码错误!");
            }
        } else {
            response.getWriter().println("用户名不存在!");
        }
    }
}
