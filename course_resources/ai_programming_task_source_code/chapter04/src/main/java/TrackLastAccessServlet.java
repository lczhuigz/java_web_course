import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
// 该Servlet用于跟踪用户的最后一次访问时间
@WebServlet("/trackLastAccess")
public class TrackLastAccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型和字符集
        response.setContentType("text/html;charset=UTF-8");
        // 获取所有的Cookie
        Cookie[] cookies = request.getCookies();
        String lastAccessTime = null;
        if (cookies != null) {
            //循环遍历所有Cookie，查找是否存在名为"lastAccessTime"的Cookie
            for (Cookie cookie : cookies) {
                if ("lastAccessTime".equals(cookie.getName())) {
                    lastAccessTime = URLDecoder.decode(cookie.getValue(), "utf-8");
                    break;
                }
            }
        }
        // 准备响应内容
        StringBuilder responseContent = new StringBuilder();
        if (lastAccessTime != null) {
            responseContent.append("上次访问时间: ").append(lastAccessTime);
        } else {
            responseContent.append("本次为第一次访问");
        }
        // 设置当前时间为新的上次访问时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = URLEncoder.encode(sdf.format(new Date()), "utf-8");
        Cookie lastAccessCookie = new Cookie("lastAccessTime", currentTime);
        // 设置Cookie有效期为一年
        lastAccessCookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(lastAccessCookie);
        // 写入响应输出流
        response.getWriter().println(responseContent.toString());
    }
}
