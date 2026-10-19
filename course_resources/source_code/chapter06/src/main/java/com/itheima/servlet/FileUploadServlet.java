package com.itheima.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //获取项目路径
        String targetPath = req.getServletContext().getRealPath("/");
        //获取名称为file的文件
        Part file = req.getPart("file");
        String avatarName = file.getSubmittedFileName();
        //文件上传路径
        String avatarPath = targetPath + "/" + avatarName;
        //上传文件
        file.write(avatarPath);
    }
}