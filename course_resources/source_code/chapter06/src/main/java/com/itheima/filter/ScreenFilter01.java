package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

//验证是否满18周岁
@WebFilter("/screenServlet")
public class ScreenFilter01 implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        int age = Integer.parseInt(req.getParameter("age"));
        if(age >= 18){
            chain.doFilter(req,resp);
        }else {
            PrintWriter pw = resp.getWriter();
            pw.println("对不起，您没有满18周岁！");
        }
    }
}