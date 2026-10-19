package case01;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * 用户登录监听器，用于监听HTTP会话的创建和销毁事件
 * 该监听器主要负责统计登录次数，即每当一个新会话创建时，登录次数递增
 */
@WebListener
public class UserLoginListener implements HttpSessionListener {

    /**
     * 监听HTTP会话创建事件
     * 每当一个新会话创建时，登录次数递增
     *
     * @param se HttpSessionEvent对象，包含会话信息
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 获取ServletContext对象，用于共享应用级别的数据
        ServletContext context = se.getSession().getServletContext();

        // 从ServletContext中获取当前登录次数
        Integer loginCount = (Integer) context.getAttribute("loginCount");
        if (loginCount == null) {
            // 如果当前登录次数为空，初始化为0
            loginCount = 0;
        }
        // 登录次数递增
        loginCount++;
        // 更新ServletContext中的登录次数
        context.setAttribute("loginCount", loginCount);
    }

   /**
     * 监听HTTP会话销毁事件
     * 目前该方法内部未实现任何操作，因为无需在会话销毁时进行处理
     *
     * @param se HttpSessionEvent对象，包含会话信息
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 可以在这里处理会话销毁时的操作，但这里不需要
    }
}
