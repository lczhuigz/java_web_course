package case02;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 自动登录过滤器
 * 该过滤器用于检查用户的登录状态，并在可能的情况下自动登录用户
 * 它通过检查会话中的用户信息或自动登录cookie来实现这一点
 */
@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    /**
     * 过滤器的主要逻辑方法
     * 检查用户是否已经登录，或者根据cookie自动登录用户
     *
     * @param request  Servlet请求对象，用于访问请求信息
     * @param response Servlet响应对象，用于向客户端发送响应
     * @param chain    过滤器链，用于将请求传递给下一个过滤器或目标资源
     * @throws IOException      如果发生I/O错误
     * @throws ServletException 如果处理过程中发生Servlet异常
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 将请求和响应对象转换为HTTP特定的类型
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取当前会话，如果没有会话则不创建新会话
        HttpSession session = httpRequest.getSession(false);
        // 获取当前请求的路径
        String servletPath = httpRequest.getServletPath();

        // 如果请求是自动登录、自动登录页面或自动登出，直接放行
        if ("/autoLogin".equals(servletPath) ||"/autoLogin.html".equals(servletPath) || "/autoLogout".equals(servletPath)) {
            chain.doFilter(request, response);
            return;
        }

        // 如果会话中包含用户信息，表明用户已登录，直接放行
        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(request, response);
        } else {
            // 获取请求中的所有cookie
            Cookie[] cookies = httpRequest.getCookies();
            // 如果存在cookie，尝试从中获取自动登录信息
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // 查找名为"autologin"的cookie
                    if ("autologin".equals(cookie.getName())) {
                        // 解析cookie值以获取用户凭证
                        String[] userCredentials = cookie.getValue().split(":");
                        // 如果凭证格式正确，进行验证
                        if (userCredentials.length == 2) {
                            String username = userCredentials[0];
                            String password = userCredentials[1];

                            // 这里可以添加用户名和密码的验证逻辑
                            // 如果验证通过，创建用户对象并存储在会话中，然后放行
                            if ("admin".equals(username) && "admin".equals(password)) {
                                User user = new User(username, password);
                                session = httpRequest.getSession();
                                session.setAttribute("user", user);
                                chain.doFilter(request, response);
                                return;
                            }
                        }
                    }
                }
            }

            // 如果用户未登录且自动登录失败，设置响应内容类型并显示登录提示信息
            httpResponse.setContentType("text/html;charset=UTF-8");
            httpResponse.getWriter().println("当前还没有登录，<a href=\"autoLogin.html\">返回登录页面</a>");
        }
    }
}
