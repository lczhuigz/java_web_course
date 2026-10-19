import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 处理购物车相关请求的Servlet
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    /**
     * 处理POST请求，主要用于向购物车中添加商品
     *
     * @param request 包含请求数据的HttpServletRequest对象
     * @param response 用于发送响应数据的HttpServletResponse对象
     * @throws ServletException 如果Servlet遇到异常
     * @throws IOException 如果发生输入或输出异常
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取服装ID，这里假设服装ID是通过表单参数传递的
        int clothesId = Integer.parseInt(request.getParameter("clothesId"));
        // 获取或创建会话，用于存储用户购物车信息
        HttpSession session = request.getSession();
        // 从会话中获取购物车项的映射，如果不存在则创建一个新的映射
        Map<Integer, CartItem> cartItems = (Map<Integer, CartItem>) session.getAttribute("cartItems");

        if (cartItems == null) {
            cartItems = new HashMap<>();
            session.setAttribute("cartItems", cartItems);
        }

        // 根据服装ID获取服装信息
        Clothes clothes = getClothesById(clothesId);
        if (clothes != null) {
            // 检查购物车中是否已存在该服装的项
            CartItem item = cartItems.get(clothesId);
            if (item == null) {
                // 如果不存在，则创建新的购物车项并添加到购物车中
                item = new CartItem(clothes, 1);
                cartItems.put(clothesId, item);
            } else {
                // 如果存在，则增加该项的数量
                item.setQuantity(item.getQuantity() + 1);
            }
        }

        // 重定向到购物车页面，显示购物车的内容
        response.sendRedirect("cart.jsp");
    }

    /**
     * 根据服装ID获取服装信息的辅助方法
     *
     * @param id 服装的唯一标识符
     * @return Clothes对象，如果找不到则返回null
     */
    private Clothes getClothesById(int id) {
        // 这里应该是从数据库或其他地方获取服装信息的逻辑
        // 为了示例，我们使用硬编码的方式返回一些固定的服装信息
        if (id == 1) {
            return new Clothes(1, "T恤", 50.0);
        } else if (id == 2) {
            return new Clothes(2, "牛仔裤", 100.0);
        } else if (id == 3) {
            return new Clothes(3, "夹克", 200.0);
        }
        // 如果ID不匹配任何已知的服装，则返回null
        return null;
    }
}
