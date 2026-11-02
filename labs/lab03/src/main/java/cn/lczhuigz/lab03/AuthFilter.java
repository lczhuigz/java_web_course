package cn.lczhuigz.lab03;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
/**
 * 认证过滤器：
 *  1) Session 有 user：已登录；
 *  2) Session 无 user 但存在有效免登录 Cookie：自动登录；
 *  3) 已登录访问 login.html：跳转主页；
 *  4) 未登录访问受保护页面：跳转登录页。
 */
@WebFilter(urlPatterns = {"/login.html", "/welcome.html", "/info.html"})
public class AuthFilter implements Filter {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String user = (session == null) ? null : (String) session.getAttribute("user");

        // ---------- ① Session 为空时，尝试用 Cookie 自动登录 ----------
        if (user == null) {
            String cookieUser = null, cookiePwd = null;
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("loginUser".equals(c.getName())) {
                        cookieUser = URLDecoder.decode(c.getValue(), "UTF-8");
                    } else if ("loginPwd".equals(c.getName())) {
                        cookiePwd = URLDecoder.decode(c.getValue(), "UTF-8");
                    }
                }
            }
            if (USERNAME.equals(cookieUser) && PASSWORD.equals(cookiePwd)) {
                session = req.getSession(true);              // 重建 Session
                session.setAttribute("user", cookieUser);    // 自动登录
                session.setMaxInactiveInterval(30 * 60);
                user = cookieUser;
            }
        }

        String uri = req.getRequestURI();

        // ---------- ② 已登录访问登录页：直接进主页 ----------
        if (user != null && uri.endsWith("/login.html")) {
            resp.sendRedirect(req.getContextPath() + "/welcome.html");
            return;
        }

        // ---------- ③ 未登录访问受保护页面：回到登录页 ----------
        if (user == null && !uri.endsWith("/login.html")) {
            resp.sendRedirect(req.getContextPath() + "/login.html?error=needLogin");
            return;
        }

        chain.doFilter(request, response);
    }
}