package com.itheima.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
@WebServlet("/responseservlet02")
public class ResponseServlet02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        // 获取图片的绝对路径
        String imagePath = 
                getServletContext().getRealPath("/img/image.jpg");
        File imageFile = new File(imagePath);
        resp.setContentType("image/jpeg");      // 设置响应内容类型为图片类型
        // 设置响应头
        resp.setHeader("Content-Disposition", "inline; filename=\"" + 
                imageFile.getName() + "\"");
        // 获取响应输出流
        OutputStream out = resp.getOutputStream();
        // 读取图片文件并写入输出流
        FileInputStream fis = new FileInputStream(imageFile);
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        fis.close();
        out.flush();
        out.close();
    }
}