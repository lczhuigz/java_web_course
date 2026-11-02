package cn.lczhuigz.lab03;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 1. 销毁 Session
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 2. 清除免登录 Cookie（MaxAge = 0 表示立即删除）
        Cookie cookieUser = new Cookie("loginUser", "");
        Cookie cookiePwd = new Cookie("loginPwd", "");
        cookieUser.setMaxAge(0);
        cookiePwd.setMaxAge(0);
        cookieUser.setPath(req.getContextPath());
        cookiePwd.setPath(req.getContextPath());
        resp.addCookie(cookieUser);
        resp.addCookie(cookiePwd);

        // 3. 回到登录页
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}