package com.itcast.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cookie01")
public class CookieServlet01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp) {
        //获取请求中的Cookie
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().contains("cookie"))
                System.out.println(cookie.getName() + ":" +
                        cookie.getValue());
        }
        Cookie cookie1 = new Cookie("cookie1", "cookie1_value");
        Cookie cookie2 = new Cookie("cookie2", "cookie2_value");
        cookie1.setPath("/chapter04/cookie01");
        cookie2.setPath("/chapter04/cookie02");
        //设置cookie1的有效时间为15秒
        cookie1.setMaxAge(15);
        //将cookie1和cookie2添加到响应中
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
    }
}