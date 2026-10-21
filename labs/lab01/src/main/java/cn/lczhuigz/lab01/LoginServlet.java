package cn.lczhuigz.lab01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

/**
 * 登录 Servlet，处理 /login 的 POST 请求。
 * 用户名和密码来自注册 Servlet 存进 ServletContext 的 userMap。
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求和响应的字符编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取用户名和密码参数
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        // 检查用户名和密码是否为空
        if (userName == null || password == null || userName.isEmpty() || password.isEmpty()) {
            printResult(response, "用户名和密码不能为空！", false);
            return;
        }

        // 从 ServletContext 中获取用户映射
        Map<String, User> userMap = (Map<String, User>) getServletContext().getAttribute("userMap");

        // 检查用户映射是否存在，并验证用户名和密码
        if (userMap == null || !userMap.containsKey(userName)) {
            printResult(response, "用户名不存在！", false);
            return;
        }

        User user = userMap.get(userName);
        if (user.getPassword().equals(password)) {
            printResult(response, "登录成功！欢迎，" + userName, true);
        } else {
            printResult(response, "密码错误！", false);
        }
    }

    /**
     * 直接访问 /login 时（GET）回到登录页，避免出现 405。
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.html");
    }

    private void printResult(HttpServletResponse response, String message, boolean success) throws IOException {
        response.getWriter().println("<!DOCTYPE html><html lang=\"zh-CN\"><head><meta charset=\"UTF-8\">"
                + "<title>登录结果</title></head><body style=\"font-family:sans-serif;text-align:center;padding-top:80px\">"
                + "<h2 style=\"color:" + (success ? "#0f766e" : "#b91c1c") + "\">" + message + "</h2>"
                + "<p><a href=\"login.html\">返回登录</a> | <a href=\"index.jsp\">返回首页</a></p>"
                + "</body></html>");
    }
}
