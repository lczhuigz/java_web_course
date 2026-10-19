import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * IndexServlet类用于处理用户访问网站时的主页请求
 * 它继承自HttpServlet，并重写了doGet方法来处理GET请求
 * 该类主要用于检查用户是否已登录，并根据登录状态显示不同的页面内容
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    /**
     * 处理GET请求的方法
     * 根据用户是否已登录，返回不同的页面内容
     * 如果用户已登录，显示欢迎信息和退出登录的链接
     * 如果用户未登录，提示用户先登录，并提供登录页面的链接
     *
     * @param request 用于获取请求信息，如用户会话
     * @param response 用于向客户端发送响应，如页面内容
     * @throws ServletException 如果Servlet无法处理请求
     * @throws IOException 如果发生输入或输出异常
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前请求的会话，如果没有会话，则不创建新的会话
        HttpSession session = request.getSession(false);
        // 设置响应内容类型为HTML，并指定字符编码为UTF-8，以确保中文显示正常
        response.setContentType("text/html;charset=utf-8");
        // 检查会话是否存在，并且会话中是否包含"user"属性，以判断用户是否已登录
        if (session != null && session.getAttribute("user") != null) {
            // 如果用户已登录，显示欢迎信息和退出登录的链接
            response.getWriter().println("欢迎访问资源！<a href=\"logout\">退出登录</a>");
        } else {
            // 如果用户未登录，提示用户先登录，并提供登录页面的链接
            response.getWriter().println("当前未登录，请先登录！<a href=\"login.html\">前往登录页面</a>");
        }
    }
}
