import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClothesServlet类用于处理与衣物相关的HTTP GET请求
 * 它继承自HttpServlet，并被注解为响应地址以"/clothes"结尾的请求
 */
@WebServlet("/clothes")
public class ClothesServlet extends HttpServlet {
    /**
     * 处理GET请求，向用户展示衣物列表
     *
     * @param request  包含请求数据的HttpServletRequest对象
     * @param response 用于发送响应数据的HttpServletResponse对象
     * @throws ServletException 如果Servlet遇到异常
     * @throws IOException      如果发生输入或输出异常
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建一个衣物列表，包含三件衣物，每件衣物都有ID、名称和价格
        List<Clothes> clothesList = new ArrayList<>();
        clothesList.add(new Clothes(1, "T恤", 50.0));
        clothesList.add(new Clothes(2, "牛仔裤", 100.0));
        clothesList.add(new Clothes(3, "夹克", 200.0));

        // 将衣物列表设置为请求属性，以便在JSP页面中访问
        request.setAttribute("clothesList", clothesList);
        // 转发请求和响应对象到clothes.jsp页面，以显示衣物列表
        request.getRequestDispatcher("clothes.jsp").forward(request, response);
    }
}
