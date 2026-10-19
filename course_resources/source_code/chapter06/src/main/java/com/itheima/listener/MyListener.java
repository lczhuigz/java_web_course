package com.itheima.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class MyListener implements ServletContextListener,
        HttpSessionListener, ServletRequestListener {
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("ServletContext对象被创建");
    }
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("ServletContext对象被销毁");
    }
    public void requestInitialized(ServletRequestEvent arg0) {
        System.out.println("ServletRequest对象被创建");
    }
    public void requestDestroyed(ServletRequestEvent arg0) {
        System.out.println("ServletRequest对象被销毁");
    }
    public void sessionCreated(HttpSessionEvent arg0) {
        System.out.println("HttpSession对象被创建");
    }
    public void sessionDestroyed(HttpSessionEvent arg0) {
        System.out.println("HttpSession对象被销毁");
    }
}