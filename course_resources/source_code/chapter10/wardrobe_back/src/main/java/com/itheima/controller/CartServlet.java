package com.itheima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.itheima.model.Cart;
import com.itheima.service.CartService;
import java.io.*;
import java.util.List;
@WebServlet(name = "CartServlet", urlPatterns = 
             {"/addToCart","/getCartDataByUser","/updateCartData",
              "/delCartData"})
public class CartServlet extends HttpServlet {
    CartService cartService = new CartService();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        String action = req.getServletPath();
        ObjectMapper om = new ObjectMapper();
       /* om.registerModule(new JavaTimeModule());*/
        BufferedReader reqReader = req.getReader();
        switch (action){
            case "/addToCart":
                System.out.println("添加购物车");
                Cart cart = om.readValue(reqReader, Cart.class);
                String addToCartMsg = cartService.addToCart(cart);
                om.writeValue(pw,addToCartMsg);
                break;
            case "/getCartDataByUser":
                System.out.println("查询指定用户的购物车数据");
                int userId = Integer.parseInt(req.getParameter("userId"));
                List<Cart> allCartData = 
                        cartService.getAllCartData(userId);
                om.writeValue(pw,allCartData);
                break;
            case "/updateCartData":
                System.out.println("修改购物车数据");
                cart = om.readValue(reqReader, Cart.class);
                int updateCount = cartService.updateCartData(cart.getId(), 
                        cart.getAmount());
                om.writeValue(pw,updateCount);
                break;
            case "/delCartData":
                System.out.println("删除购物车数据");
                cart = om.readValue(reqReader, Cart.class);
                String msg = cartService.delCartData(cart.getId());
                om.writeValue(pw,msg);
                break;
        }
    }
}