package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

//验证视力是否大于等于5.0
@WebFilter("/screenServlet")
public class ScreenFilter02 implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        double vision = Double.parseDouble(req.getParameter("vision"));
        if(vision >= 5.0){
            chain.doFilter(req,resp);
        }else {
            PrintWriter pw = resp.getWriter();
            pw.println("对不起，您的视力不满足要求！");
        }
    }
}