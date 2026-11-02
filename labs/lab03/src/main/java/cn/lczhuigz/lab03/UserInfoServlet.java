package cn.lczhuigz.lab03;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 返回当前登录用户信息（JSON），供 HTML 页面 fetch 使用
 */
@WebServlet("/userinfo")
public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(false);
        String user = (session == null) ? null : (String) session.getAttribute("user");

        // 未登录：返回 401 + 简单 JSON
        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print("{\"code\":401,\"msg\":\"未登录\"}");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sessionId = session.getId();
        String createTime = sdf.format(new Date(session.getCreationTime()));
        String lastTime = sdf.format(new Date(session.getLastAccessedTime()));
        int maxInactive = session.getMaxInactiveInterval();

        // 手拼 JSON（实验无需引入第三方库）
        String json = "{"
                + "\"code\":200,"
                + "\"user\":\"" + user + "\","
                + "\"sessionId\":\"" + sessionId + "\","
                + "\"createTime\":\"" + createTime + "\","
                + "\"lastTime\":\"" + lastTime + "\","
                + "\"maxInactive\":" + maxInactive
                + "}";
        out.print(json);
    }
}