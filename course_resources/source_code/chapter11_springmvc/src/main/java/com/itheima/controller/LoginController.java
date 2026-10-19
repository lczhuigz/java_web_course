package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/user")
public class LoginController {
    //处理登录请求
    @PostMapping("/login")
    public ModelAndView login(@RequestParam("name") String name, 
        @RequestParam("password") String password){
        ModelAndView modelAndView = new ModelAndView();
        if(name.equals("heima") && password.equals("123456")){
            //验证成功，将用户名保存到模型中
            modelAndView.addObject("name",name);
            //跳转到欢迎页面
            modelAndView.setViewName("welcome");
            return modelAndView;
        }else {
            //验证失败，跳转到登录页面
            modelAndView.setViewName("login");
            modelAndView.addObject("loginError","用户名或密码错误！");
            return modelAndView;
        }
    }
}