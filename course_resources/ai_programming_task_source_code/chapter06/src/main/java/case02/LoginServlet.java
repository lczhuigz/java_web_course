package case02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录Servlet类，用于处理用户登录请求
 */
@WebServlet("/autoLogin")
public class LoginServlet extends HttpServlet {
    /**
     * 处理用户登录的POST请求
     *
     * @param request 用于获取用户输入的用户名、密码和自动登录选项
     * @param response 用于向客户端发送响应，包括设置Cookie和页面重定向
     * @throws ServletException 如果Servlet遇到异常
     * @throws IOException 如果发生输入或输出异常
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户输入的用户名、密码和自动登录选项
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String autoLogin = request.getParameter("autologin");

        // 这里可以添加用户名和密码的验证逻辑
        // 如果用户名和密码匹配，创建用户对象并存储到Session中
        if ("admin".equals(username) && "admin".equals(password)) {
            User user = new User(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // 如果用户选择自动登录，设置相应的Cookie
            if (!"no".equals(autoLogin)) {
                Cookie cookie = new Cookie("autologin", username + ":" + password);
                int maxAge = 0;
                // 根据用户选择的自动登录时长，设置Cookie的存活时间
                switch (autoLogin) {
                    case "oneWeek":
                        maxAge = 7 * 24 * 60 * 60;
                        break;
                    case "oneMonth":
                        maxAge = 30 * 24 * 60 * 60;
                        break;
                    case "threeMonths":
                        maxAge = 90 * 24 * 60 * 60;
                        break;
                }
                cookie.setMaxAge(maxAge);
                response.addCookie(cookie);
            }

            // 登录成功后重定向到主页
            response.sendRedirect("index");
        } else {
            // 如果登录失败，向客户端发送失败信息并提供返回登录页面的链接
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("登录失败，<a href=\"autoLogin.html\">返回登录页面</a>");
        }
    }
}
