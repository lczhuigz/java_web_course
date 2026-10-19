import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * CheckUsernameServlet用于检查用户提交的用户名是否已被注册
 * 该Servlet通过读取存储用户信息的JSON文件，检查用户名的唯一性
 */
@WebServlet("/CheckUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
    /**
     * 重写service方法处理客户端请求
     * 该方法首先获取客户端提交的用户名，然后从JSON文件中读取用户列表，
     * 并检查是否有重复的用户名如果用户名是唯一的，返回可以使用的消息，
     * 否则返回已注册的消息
     *
     * @param request 用于获取请求信息，如用户输入的用户名
     * @param response 用于向客户端发送响应信息，告知用户名是否可用
     * @throws ServletException 如果Servlet遇到异常
     * @throws IOException 如果在读取或写入过程中发生I/O错误
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取客户端提交的用户名
        String username = request.getParameter("username");
        // 创建ObjectMapper实例，用于处理JSON数据
        ObjectMapper objectMapper = new ObjectMapper();
        // 获取JSON文件的路径
        String filePath = request.getServletContext().getRealPath("/")+ "data.json";
        // 根据路径创建File对象
        File jsonFile = new File(filePath);
        // 从JSON文件中读取用户列表
        List<User> users = objectMapper.readValue(jsonFile, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        // 初始化标志变量，用于表示用户名是否可用
        boolean flag = true;
        // 遍历用户列表，检查是否有重复的用户名
        for (User user : users) {
            if (user.getName().equals(username)) {
                // 如果发现重复的用户名，设置标志为false
                flag= false;
            }
        }
        // 设置响应内容类型和字符编码
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // 根据标志变量的值，向客户端发送响应信息
        response.getWriter().write("{\"message\":\"" + (flag ? "当前用户名可以使用" : "当前用户名已经被注册，请重新填写！") + "\"}");
    }

}
