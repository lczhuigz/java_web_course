package com.itheima.servlet;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/download")
public class FileDownloadServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws  IOException {
        resp.setContentType("text/html;charset=UTF-8");
        //获取请求中的文件名
        String fileName = req.getParameter("fileName");
        //参数非空判断
        if(fileName == null || fileName.trim().isEmpty()){
            resp.getWriter().println("请输入要下载的文件的名称！");
            return;
        }
        //获取要下载的文件存放目录
        String folder = 
                req.getServletContext().getRealPath("/");
        //根据指定文件名创建File对象
        File file = new File(folder + fileName);
        //如果文件存在就进行下载
        if(file.exists() && file.isFile()){
            //通知浏览器以下载的方式打开
            resp.addHeader("Content-Type","application/octet-stream");
            resp.addHeader("Content-Disposition", 
                    "attachment;filename=" + fileName);
            //获取字节输入流
            InputStream is = new FileInputStream(file);
            //获取字节输出流
            ServletOutputStream os = resp.getOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            //将文件内容写入响应
            while ((len = is.read(bytes)) != -1){
                os.write(bytes,0,len);
            }
            os.close();
            is.close();
        }else{
            resp.getWriter().println("文件不存在，请重试！");
        }
    }
}