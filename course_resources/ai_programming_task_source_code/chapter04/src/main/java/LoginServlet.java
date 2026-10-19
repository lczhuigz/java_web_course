import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// 处理用户登录请求的Servlet
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
     * 处理POST请求的登录逻辑
     *
     * @param request 包含用户名和密码的请求对象
     * @param response 用于响应登录结果的响应对象
     * @throws ServletException 如果Servlet遇到异常
     * @throws IOException 如果发生输入或输出异常
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户名和密码参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 设置响应内容类型为HTML，编码为UTF-8
        response.setContentType("text/html;charset=utf-8");
        // 这里仅作示例，实际应用中应从数据库或其他安全存储中获取用户信息
        if ("admin".equals(username) && "password".equals(password)) {
            // 如果用户名和密码匹配成功，创建会话并存储用户信息
            HttpSession session = request.getSession();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            session.setAttribute("user", user);
            // 重定向到主页
            response.sendRedirect("index");
        } else {
            // 如果登录失败，显示错误信息并提供重新登录的链接
            response.getWriter().println("登录失败，请重新登录！<a href=\"login.html\">前往登录页面</a>");
        }
    }
}
