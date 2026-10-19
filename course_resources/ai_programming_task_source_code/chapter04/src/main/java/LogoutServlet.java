import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LogoutServlet类用于处理用户注销请求
 * 它通过销毁用户的会话来实现注销功能
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    /**
     * 处理GET请求，用于用户注销
     * 此方法首先尝试获取当前请求的会话如果会话存在，则移除会话中的用户信息，
     * 并且使整个会话失效最后，通过重定向响应将用户返回到登录页面
     *
     * @param request  请求对象，用于获取会话信息
     * @param response 响应对象，用于重定向用户到登录页面
     * @throws ServletException 如果Servlet遇到异常
     * @throws IOException      如果发生输入或输出异常
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前请求的会话，如果没有则不创建新会话
        HttpSession session = request.getSession(false);
        // 检查会话是否存在
        if (session != null) {
            // 移除会话中的用户信息
            session.removeAttribute("user");
            // 使整个会话失效
            session.invalidate();
        }
        // 重定向用户到登录页面
        response.sendRedirect("login.html");
    }
}
