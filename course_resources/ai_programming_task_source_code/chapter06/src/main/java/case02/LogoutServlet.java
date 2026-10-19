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
 * LogoutServlet类用于处理用户自动登出的请求
 * 它通过销毁会话和清除自动登录的Cookie来实现登出功能
 */
@WebServlet("/autoLogout")
public class LogoutServlet extends HttpServlet {
    /**
     * 处理GET请求，用于用户登出
     *
     * @param request 用于获取会话和Cookies的HttpServletRequest对象
     * @param response 用于重定向到登录页面和清除Cookie的HttpServletResponse对象
     * @throws ServletException 如果Servlet遇到异常
     * @throws IOException 如果发生输入或输出异常
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前会话，如果存在则使其无效，以实现用户登出
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 获取所有Cookies，如果存在，则遍历以找到并清除自动登录的Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 当找到名为"autologin"的Cookie时，设置其最大存活时间为0，以清除该Cookie
                if ("autologin".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        // 重定向用户到自动登录页面，完成登出操作
        response.sendRedirect("autoLogin.html");
    }
}
