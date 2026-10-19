package com.itheima.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/axiosServlet")
public class AxiosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        // 创建一个User对象
        User user =null;
        if("1".equals(id)){
            user = new User("lisi",18,"女");
        }
        if("2".equals(id)){
            user = new User("zhangsan",25,"男");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        //将User对象转换为JSON格式的字符串
        String userJson = objectMapper.writeValueAsString(user);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        //将JSON格式的数据进行响应
        writer.println(userJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 读取请求体中的JSON数据
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader reader = req.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        // 创建ObjectMapper对象
        ObjectMapper objectMapper = new ObjectMapper();
        // 将JSON数据转换为User对象
        User u = objectMapper.readValue(sb.toString(), User.class);
        System.out.println("JSON格式的数据转换为User对象：\n" + u);
        PrintWriter writer = resp.getWriter();
        //写入响应信息
        writer.println("获取成功");
    }
}
