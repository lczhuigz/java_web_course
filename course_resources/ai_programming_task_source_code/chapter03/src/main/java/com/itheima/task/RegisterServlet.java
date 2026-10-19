package com.itheima.task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册Servlet，处理用户注册请求
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    /**
     * 处理POST请求，用于用户注册
     *
     * @param request  请求对象，包含用户输入的用户名和密码
     * @param response 响应对象，用于向用户返回注册结果
     * @throws ServletException 如果Servlet出现异常
     * @throws IOException      如果输入输出操作出现异常
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应的字符编码，确保中文字符不乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取用户输入的用户名和密码
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        // 检查用户名和密码是否为空
        if (userName == null || password == null || userName.isEmpty() || password.isEmpty()) {
            response.getWriter().println("用户名和密码不能为空!");
            return;
        }

        // 从ServletContext中获取已注册的用户信息
        Map<String, User> userMap = (Map<String, User>) getServletContext().getAttribute("userMap");
        // 如果userMap为空，则创建一个新的HashMap并存入ServletContext
        if (userMap == null) {
            userMap = new HashMap<>();
            getServletContext().setAttribute("userMap", userMap);
        }

        // 检查用户名是否已存在
        if (userMap.containsKey(userName)) {
            response.getWriter().println("用户名不能重复!");
        } else {
            // 创建User对象并存入userMap，然后重定向到登录页面
            User user = new User(userName, password);
            userMap.put(userName, user);
            response.sendRedirect("taskLogin.html");
        }
    }
}
