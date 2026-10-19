package com.itheima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.itheima.model.Clothes;
import com.itheima.service.ClothesService;
import java.io.*;
import java.util.List;
@WebServlet(name = "ManageClothesServlet", urlPatterns = 
          {"/getAllClothesData","/addClothes","/searchClothes",
           "/delClothes","/editClothes"})
public class ManageClothesServlet extends HttpServlet {
    ClothesService clothesService = new ClothesService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse 
            resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        String action = req.getServletPath();
        ObjectMapper om = new ObjectMapper();
        BufferedReader reqReader = req.getReader();
        switch (action){
            case "/getAllClothesData":
                //获取所有（根据服装类别或风格）服装
                List<Clothes> allClothes = 
                        clothesService.getAllClothesData();
                om.writeValue(pw,allClothes);
                break;
            case "/addClothes":
                //添加服装
                Clothes clothes = om.readValue(reqReader, Clothes.class);
                String addMsg = clothesService.addClothes(clothes);
                om.writeValue(pw,addMsg);
                break;
            case "/searchClothes":
                //多条件查询服装
                String clothName = req.getParameter("clothName");
                String style = req.getParameter("style");
                String type = req.getParameter("type");
                List<Clothes> searchClothes = 
                  clothesService.getClothesByParams(clothName,style,type);
                om.writeValue(pw,searchClothes);
                break;
            case "/editClothes":
                //修改服装信息
                Clothes editClothes = om.readValue(reqReader, 
                        Clothes.class);
                String editMsg = clothesService.editClothes(editClothes);
                om.writeValue(pw,editMsg);
                break;
            case "/delClothes":
                //删除（下架）服装
                Clothes delClothes = om.readValue(reqReader, 
                        Clothes.class);
                String delMsg = 
                        clothesService.delClothes(delClothes.getId());
                om.writeValue(pw,delMsg);
                break;
        }
    }
}