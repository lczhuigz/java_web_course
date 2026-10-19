package com.itheima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.itheima.model.*;
import com.itheima.service.ClothesService;
import java.io.*;
import java.util.List;
@WebServlet(name = "ClothesServlet", urlPatterns =
        {"/allClothes","/allStyles","/allTypes","/clothesByName","/clothDetails"})
public class ClothesServlet extends HttpServlet {
    ClothesService clothesService = new ClothesService();
    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse  resp)  throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        String action = req.getServletPath();
        ObjectMapper om = new ObjectMapper();
        switch (action){
            case "/allClothes":
                //获取指定类别或风格的服装
                String style = req.getParameter("style");
                String type = req.getParameter("type");
                List<Clothes> allClothes = 
                        clothesService.getAllClothes(style,type);
                om.writeValue(pw,allClothes);
                break;
            case "/allStyles":
                //获取所有风格
                List<String> allStyles = clothesService.getAllStyles();
                om.writeValue(pw,allStyles);
                break;
            case "/allTypes":
                //获取所有类型
                List<Type> allTypes = clothesService.getAllTypes();
                om.writeValue(pw,allTypes);
                break;
            case "/clothesByName":
                //根据服装名称获取服装
                String name = req.getParameter("clothesName");
                List<Clothes> clothes = clothesService.getClothesByName(name);
                om.writeValue(pw,clothes);
                break;
            case "/clothDetails":
                //查看服装详情
                int clothId = Integer.parseInt(req.getParameter("clothId"));
                Clothes clothDetails = clothesService.getClothDetails(clothId);
                om.writeValue(pw,clothDetails);
                break;
        }
    }
}