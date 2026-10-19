package com.itcast.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/cookie02")
public class CookieServlet02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp)  {
        //获取请求中的Cookie
        Cookie[] cookies = req.getCookies();
            for(Cookie cookie : cookies){
                if (cookie.getName().contains("cookie"))
                    System.out.println(cookie.getName() + ":" +
                            cookie.getValue());
            }
    }
}