package case02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * IndexServlet类用于处理用户登录状态检查和相应页面的展示
 * 它通过@WebServlet注解映射到"/index"路径
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    /**
     * 处理GET请求的方法根据用户是否已登录，返回不同的页面
     * 如果用户已登录（session中存在"user"属性），则显示访问成功页面
     * 否则，显示未登录状态，并提供登录页面的链接
     *
     * @param request  HttpServletRequest对象，用于获取请求信息，如session
     * @param response HttpServletResponse对象，用于设置响应内容类型和写入响应内容
     * @throws ServletException 如果Servlet遇到异常
     * @throws IOException      如果发生输入或输出异常
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前请求的Session，如果没有则不创建新Session
        HttpSession session = request.getSession(false);
        // 检查Session是否存在，且包含"user"属性，表示用户已登录
        if (session != null && session.getAttribute("user") != null) {
            // 设置响应内容类型为HTML，并指定字符集为UTF-8
            response.setContentType("text/html;charset=UTF-8");
            // 向响应中写入HTML内容，提示访问成功，并提供退出登录的链接
            response.getWriter().println("访问成功，<a href=\"autoLogout\">退出登录</a>");
        } else {
            // 如果用户未登录，设置响应内容类型为HTML，并指定字符集为UTF-8
            response.setContentType("text/html;charset=UTF-8");
            // 向响应中写入HTML内容，提示当前未登录状态，并提供登录页面的链接
            response.getWriter().println("当前还没有登录，<a href=\"autoLogin.html\">返回登录页面</a>");
        }
    }
}
