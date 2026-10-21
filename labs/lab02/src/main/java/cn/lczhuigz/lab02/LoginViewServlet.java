package cn.lczhuigz.lab02;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginView")
public class LoginViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应类型和编码，防止中文乱码
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 输出登录页面的HTML
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>用户登录</title>");
        out.println("<style>");
        out.println("body { display: flex; justify-content: center; align-items: center; height: 100vh; background-color: #f0f2f5; font-family: sans-serif; margin: 0; }");
        out.println(".card { background: white; padding: 40px; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); width: 350px; }");
        out.println("h1 { font-size: 24px; margin-bottom: 10px; color: #333; }");
        out.println(".subtitle { color: #888; margin-bottom: 20px; font-size: 14px; }");
        out.println(".field { margin-bottom: 15px; }");
        out.println("label { display: block; margin-bottom: 5px; color: #555; font-size: 14px; }");
        out.println("input { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 5px; box-sizing: border-box; }");
        out.println(".btn { width: 100%; padding: 10px; background-color: #5c6bc0; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; margin-top: 10px; }");
        out.println(".btn:hover { background-color: #3f51b5; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<main class='card'>");
        out.println("<span style='color: #5c6bc0; font-weight: bold;'>lab02</span>");
        out.println("<h1>用户登录</h1>");
        out.println("<p class='subtitle'>请输入账号信息以继续</p>");

        // 注意：这里表单的 action 指向 UserLoginServlet 的映射路径 /login
        out.println("<form action='login' method='post'>");
        out.println("<div class='field'>");
        out.println("<label for='userName'>用户名</label>");
        out.println("<input type='text' id='userName' name='userName' placeholder='请输入用户名' required>");
        out.println("</div>");
        out.println("<div class='field'>");
        out.println("<label for='password'>密码</label>");
        out.println("<input type='password' id='password' name='password' placeholder='请输入密码' required>");
        out.println("</div>");
        out.println("<button type='submit' class='btn'>登录</button>");
        out.println("</form>");
        out.println("<p style='text-align: center; margin-top: 15px; font-size: 14px;'>没有账号？<a href='regView' style='color: #5c6bc0; text-decoration: none;'>立即注册</a></p>");
        out.println("</main>");
        out.println("</body>");
        out.println("</html>");
    }
}