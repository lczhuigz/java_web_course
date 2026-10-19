package com.itheima.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.itheima.model.Order;
import com.itheima.service.OrderService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
@WebServlet(name = "OrderServlet", urlPatterns =
        {"/addOrder","/getOrderByUser","/delOrderData","/payOrder",
        "/receiveOrder"})
public class OrderServlet extends HttpServlet {
    OrderService orderService = new OrderService();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws  IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        String action = req.getServletPath();
        ObjectMapper om = new ObjectMapper();
        BufferedReader reqReader = req.getReader();
        switch (action){
            case "/addOrder":
                String addOrderMsg = "";
                List<Order> orderList = om.readValue(reqReader, new 
                        TypeReference<>() {});
                for (Order order : orderList) {
                    addOrderMsg = orderService.addOrder(order);
                }
                om.writeValue(pw,addOrderMsg);
                break;
            case "/getOrderByUser":
                int userId = Integer.parseInt(req.getParameter("userId"));
                int status = Integer.parseInt(req.getParameter("status"));
                List<Order> orderByUser = 
                       orderService.getOrderByUser(userId,status);
                om.writeValue(pw,orderByUser);
                break;
            case "/delOrderData":
                Order delOrder = om.readValue(reqReader,Order.class);
                String delMsg = 
                         orderService.delOrderData(delOrder.getId());
                om.writeValue(pw,delMsg);
                break;
            case "/payOrder":
                //支付（将未支付状态：0，改为未发货状态：1）
                Order updateOrder = om.readValue(reqReader,Order.class);
                int count = 
                  orderService.upDateOrderStatus(updateOrder.getId(),0,1);
                if(count > 0){
                    om.writeValue(pw,"支付成功！");
                }else {
                    om.writeValue(pw,"支付失败！");
                }
                break;
            case "/receiveOrder":
                //收货(将已发货状态：2，改为已收货状态：3)
                updateOrder = om.readValue(reqReader,Order.class);
                count =                        
                  orderService.upDateOrderStatus(updateOrder.getId(),2,3);
                if(count > 0){
                    om.writeValue(pw,"收货成功！");
                }else {
                    om.writeValue(pw,"收货失败！");
                }
                break;
        }
    }
}