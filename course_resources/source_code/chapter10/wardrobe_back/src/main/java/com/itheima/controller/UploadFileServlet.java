package com.itheima.controller;

import com.itheima.utils.LocalDateTimeUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@WebServlet(name = "UploadFileServlet", urlPatterns ="/uploadFile")
@MultipartConfig
public class UploadFileServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
           throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        //指定上传路径
        String targetPath = 
                req.getServletContext().getRealPath("/images");
        //获取Part对象
        Part part = req.getPart("clothesImage");
        String fileName = part.getSubmittedFileName();
        // 添加时间戳前缀
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestampPrefix = formatter.format(LocalDateTime.now());
        String newFileName = timestampPrefix + "_" + fileName;
        //上传到服务器
        String TomcatPath = targetPath + "/" + newFileName;
        part.write(TomcatPath);
        //复制到当前项目的webapp目录下，防止重启服务器文件被覆盖
        String CurrentPath = "E:\\idea\\web03\\chapter10\\wardrobe_back" +
                "\\src\\main\\webapp\\images\\" + newFileName;
        File srcFile = new File(TomcatPath);
        File destFile = new File(CurrentPath);
        FileUtils.copyFile(srcFile,destFile);
        pw.println(newFileName);
    }
}