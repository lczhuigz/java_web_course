package com.itcast.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp)  {
        //获取请求中的取款金额
        double withdrawAmount = 
                Double.parseDouble(req.getParameter("amount"));
        //获取HttpSession对象
        HttpSession session = req.getSession();
        //获取取款钱的金额
        Double balance = (Double) session.getAttribute("balance");
        System.out.println("您的银行卡号为：" + session.getId());
        System.out.println("取款前的余额（元）：" + balance );
        System.out.println("本次取款金额（元）：" + withdrawAmount);
        //取款操作
        balance = balance - withdrawAmount;
        if(balance >= 0){
            session.setAttribute("balance", balance);
            System.out.println("您的余额（元）：" + balance);
        }else {
            System.out.println("余额不足！");
        }
    }
}