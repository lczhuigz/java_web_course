package com.itheima.filter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.UserDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import com.itheima.model.User;
import java.io.*;
//访问过滤器类
@WebFilter(servletNames = {"CartServlet","OrderServlet","UserServlet","ManageClothesServlet","ManageOrderServlet","ManageUserServlet"})
public class AccessFilter implements Filter {
    UserDao userDao = new UserDao();
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        PrintWriter pw = resp.getWriter();
        ObjectMapper om = new ObjectMapper();
        //检查请求是否携带Token，若未携带，则进行拦截
        String token = request.getHeader("token");
        if(token != null){
            //如果请求携带token，则查询该token对应的用户
            Integer userId;
            try {
                //从token中解析用户id
                userId = Integer.parseInt(JWT.decode(token)
                           .getAudience().get(0));
                User user = userDao.getUserById(userId);
                if (user == null) {
                    System.out.println("用户不存在");
                    return;
                }
                // 如果查找到了，则说明token验证通过，则放行
                chain.doFilter(request,response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            //如果未携带Token，则拦截
            om.writeValue(pw,"暂无权限！");
        }
    }
}