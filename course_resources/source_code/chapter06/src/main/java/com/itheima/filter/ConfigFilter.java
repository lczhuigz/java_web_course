package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebFilter(urlPatterns = "/myServlet",
        initParams = @WebInitParam(name = "username", value = "admin,lisi"))
public class ConfigFilter implements Filter {
    private FilterConfig config;
    @Override
    public void init(FilterConfig filterConfig) {
        this.config = filterConfig;
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
       //获取请求中参数username的值
        String username = req.getParameter("username");
        //获取初始参数username的值
        String value = config.getInitParameter("username");
        //解析初始参数的值
        String[] split = value.split(",");
        boolean contains = Arrays.asList(split).contains(username);
        //如果请求参数的值包含在初始参数的值中，则放行
        if (contains) {
            chain.doFilter(req, resp);
            return;
        }
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.println("当前账号存在风险，暂时不能为您提供服务");
    }
}