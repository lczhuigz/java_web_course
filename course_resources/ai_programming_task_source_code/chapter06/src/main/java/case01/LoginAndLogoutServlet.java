package case01;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 处理用户登录和登出请求的Servlet类
 * 该类覆盖了HttpServlet的service方法，以处理/login和/logout路径的请求
 */
@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginAndLogoutServlet extends HttpServlet {

    /**
     * 处理HTTP请求的入口方法
     * 根据请求的路径决定是处理登录还是处理登出
     *
     * @param request  包含请求数据的HttpServletRequest对象
     * @param response 用于发送响应数据的HttpServletResponse对象
     * @throws IOException 如果发生I/O错误
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取请求的路径
        String action = request.getServletPath();
        // 根据路径调用相应的处理方法
        if ("/login".equals(action)) {
            handleLogin(request, response);
        } else if ("/logout".equals(action)) {
            handleLogout(request, response);
        } else {
            // 如果请求路径既不是/login也不是/logout，则返回404错误
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 处理用户登录请求的方法
     * 验证用户名和密码，并在成功时创建会话，以及更新登录次数
     *
     * @param request  包含请求数据的HttpServletRequest对象
     * @param response 用于发送响应数据的HttpServletResponse对象
     * @throws IOException 如果发生I/O错误
     */
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取用户名和密码参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 这里可以添加用户名和密码的验证逻辑
        if ("admin".equals(username) && "admin".equals(password)) {
            // 验证成功，创建或获取现有会话，并设置会话存活时间
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(12 * 60 * 60); // 设置会话存活时间为12小时

            // 获取ServletContext对象，用于访问全局变量
            ServletContext context = request.getServletContext();
            // 获取当前登录次数，并在响应中显示
            Integer loginCount = (Integer) context.getAttribute("loginCount");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("当前登录次数: " + loginCount + "，<a href=\"logout\">退出登录</a>");
        } else {
            // 验证失败，返回登录失败信息
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("登录失败，<a href=\"login.html\">返回登录页面</a>");
        }
    }

    /**
     * 处理用户登出请求的方法
     * 作废用户的会话，并返回登出成功信息
     *
     * @param request  包含请求数据的HttpServletRequest对象
     * @param response 用于发送响应数据的HttpServletResponse对象
     * @throws IOException 如果发生I/O错误
     */
    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取当前请求的会话，如果存在
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 会话存在，作废会话
            session.invalidate();
        }
        // 返回登出成功信息
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("退出成功，<a href=\"login.html\">返回登录页面</a>");
    }
}
