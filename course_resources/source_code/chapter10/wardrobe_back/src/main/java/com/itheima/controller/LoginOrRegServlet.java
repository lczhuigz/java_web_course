package com.itheima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.model.User;
import com.itheima.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
@WebServlet( urlPatterns ={"/register","/login"})
public class LoginOrRegServlet  extends HttpServlet {
    UserService userService = new UserService();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        //获取当前请求的URL
        String action = req.getServletPath();
        ObjectMapper om = new ObjectMapper();
        PrintWriter pw = resp.getWriter();
        BufferedReader reqReader = req.getReader();
        //如果为注册请求，则进行注册成立
        if("/register".equals(action)){
            User user = om.readValue(reqReader, User.class);
            String registerMsg = userService.register(user);
            om.writeValue(pw,registerMsg);
        }
        if("/login".equals(action)){
            String userInfo = req.getParameter("userInfo");
            String password = req.getParameter("password");
            boolean isAdminLogin =
                    Boolean.parseBoolean(req.getParameter("isAdminLogin"));
            User loginUser = userService.login(userInfo, password);
            //当登录后台管理系统时
            if(isAdminLogin){
                //判断是管理员还是普通用户
                int role = loginUser.getRole();
                if(role != 1){
                    //是普通用户
                    om.writeValue(pw,"您没有权限！");
                }
            }
            om.writeValue(pw,loginUser);
        }
    }
}