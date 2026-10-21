package cn.lczhuigz.lab01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册 Servlet，处理 /register 的 POST 请求。
 * 注册成功的用户放进 ServletContext 的 userMap，供登录 Servlet 校验。
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求和响应的字符编码，确保中文字符不乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取用户输入的用户名和密码
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        // 检查用户名和密码是否为空
        if (userName == null || password == null || userName.isEmpty() || password.isEmpty()) {
            printResult(response, "用户名和密码不能为空！");
            return;
        }

        // 从 ServletContext 中获取已注册的用户信息
        Map<String, User> userMap = (Map<String, User>) getServletContext().getAttribute("userMap");
        // 如果 userMap 为空，则创建一个新的 Map 并存入 ServletContext
        if (userMap == null) {
            userMap = new ConcurrentHashMap<>();
            getServletContext().setAttribute("userMap", userMap);
        }

        // 检查用户名是否已存在
        if (userMap.containsKey(userName)) {
            printResult(response, "用户名不能重复！");
            return;
        }

        // 保存用户，然后重定向到登录页（带上标记，登录页会提示注册成功）
        userMap.put(userName, new User(userName, password));
        response.sendRedirect("login.html?registered=1");
    }

    /**
     * 直接访问 /register 时（GET）回到注册页，避免出现 405。
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("register.html");
    }

    private void printResult(HttpServletResponse response, String message) throws IOException {
        response.getWriter().println("<!DOCTYPE html><html lang=\"zh-CN\"><head><meta charset=\"UTF-8\">"
                + "<title>注册结果</title></head><body style=\"font-family:sans-serif;text-align:center;padding-top:80px\">"
                + "<h2 style=\"color:#b91c1c\">" + message + "</h2>"
                + "<p><a href=\"register.html\">返回注册</a></p>"
                + "</body></html>");
    }
}
