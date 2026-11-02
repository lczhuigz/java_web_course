package cn.lczhuigz.lab03;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 登录处理：校验账号 -> 写 Session ->（可选）写两周免登录 Cookie
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    /** 固定的用户名 / 密码 */
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");   // 勾选时为 "on"

        // ---------- 1. 校验账号密码 ----------
        if (!USERNAME.equals(username) || !PASSWORD.equals(password)) {
            // 失败：重定向回登录页并带错误标记（HTML 页面用 JS 显示）
            resp.sendRedirect(req.getContextPath() + "/login.html?error=1");
            return;
        }

        // ---------- 2. 写 Session：登录成功后即可操作其它功能 ----------
        HttpSession session = req.getSession();
        session.setAttribute("user", username);
        session.setMaxInactiveInterval(30 * 60);   // Session 30 分钟不活动失效

        // ---------- 3. 勾选"两周不用登录"：写 Cookie ----------
        if ("on".equals(remember)) {
            Cookie cookieUser = new Cookie("loginUser",
                    URLEncoder.encode(username, "UTF-8"));
            Cookie cookiePwd = new Cookie("loginPwd",
                    URLEncoder.encode(password, "UTF-8"));

            int maxAge = 14 * 24 * 60 * 60;   // 14 天 = 1209600 秒
            cookieUser.setMaxAge(maxAge);
            cookiePwd.setMaxAge(maxAge);

            // 路径要覆盖整个项目，否则其它页面取不到
            cookieUser.setPath(req.getContextPath());
            cookiePwd.setPath(req.getContextPath());

            resp.addCookie(cookieUser);
            resp.addCookie(cookiePwd);
        }

        // ---------- 4. 登录成功，跳转主页 ----------
        resp.sendRedirect(req.getContextPath() + "/welcome.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}