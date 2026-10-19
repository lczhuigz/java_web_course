import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * ManageClothesServlet类用于处理与衣物管理相关的HTTP请求
 * 它继承自HttpServlet，并重写了service方法来处理不同的HTTP请求
 * 该类使用了Jackson库的ObjectMapper来处理JSON数据，以及ClothesDao来与数据库交互
 */
@WebServlet({"/add", "/update", "/all", "/del", "/selectByName"})
public class ManageClothesServlet extends HttpServlet {
    // ClothesDao实例，用于执行与衣物相关的数据库操作
    private ClothesDao clothesDao = new ClothesDao();
    // ObjectMapper实例，用于JSON序列化和反序列化
    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 重写service方法，根据请求路径分发到不同的操作
     *
     * @param request  包含请求数据的HttpServletRequest对象
     * @param response 用于发送响应数据的HttpServletResponse对象
     * @throws ServletException 如果Servlet遇到异常
     * @throws IOException      如果发生输入或输出异常
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的路径
        String path = request.getServletPath();
        // 根据路径执行相应的操作
        switch (path) {
            case "/add":
                // 从请求中读取并解析Clothes对象，然后添加到数据库
                Clothes addClothes = objectMapper.readValue(request.getInputStream(), Clothes.class);
                clothesDao.addClothes(addClothes);
                // 返回添加成功的消息
                objectMapper.writer().writeValue(response.getWriter(), "添加成功");
                break;
            case "/update":
                // 从请求中读取并解析Clothes对象，然后更新数据库中的记录
                Clothes updateClothes = objectMapper.readValue(request.getInputStream(), Clothes.class);
                clothesDao.updateClothes(updateClothes);
                // 返回更新成功的消息
                objectMapper.writer().writeValue(response.getWriter(), "修改成功");
                break;
            case "/all":
                // 从数据库中获取所有衣物，并以JSON格式返回
                List<Clothes> allClothes = clothesDao.getAllClothes();
                response.getWriter().write(objectMapper.writeValueAsString(allClothes));
                break;
            case "/del":
                // 从请求中读取并解析Clothes对象，然后从数据库中删除对应的记录
                Clothes delClothes = objectMapper.readValue(request.getReader(), Clothes.class);
                clothesDao.deleteClothes(delClothes.getId());
                // 返回删除成功的消息
                objectMapper.writer().writeValue(response.getWriter(), "删除成功");
                break;
            case "/selectByName":
                // 从请求中读取并解析Clothes对象，然后根据名称从数据库中选择记录
                Clothes selectClothes = objectMapper.readValue(request.getReader(), Clothes.class);
                List<Clothes> clothesByName = clothesDao.selectByName(selectClothes.getName());
                // 以JSON格式返回查询结果
                response.getWriter().write(objectMapper.writeValueAsString(clothesByName));
                break;
            default:
                // 如果请求路径不匹配任何已知操作，返回404错误
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}