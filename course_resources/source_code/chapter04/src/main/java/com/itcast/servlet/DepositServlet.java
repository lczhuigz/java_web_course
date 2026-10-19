package com.itcast.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp)  {
        //获取请求参数中的存款金额
        double depositAmount = 
                Double.parseDouble(req.getParameter("amount"));
        //获取HttpSession对象
        HttpSession session = req.getSession();
        //存款操作
        //如果是第一次存款，则余额为存款金额
        double balance = depositAmount;
        Object b = session.getAttribute("balance");
        //判断当前HttpSession对象是否存在balance属性
        if(b!=null){
            //若不为null,则说明不是第一次存款，余额为这次存款加上次余额
            balance = (double) b + depositAmount;
        }
        //存储余额数据
        session.setAttribute("balance", balance);
        System.out.println("您的银行卡号为：" + session.getId());
        System.out.println("本次存款金额（元）：" + depositAmount );
        System.out.println("您的余额（元）：" + balance );
    }
}