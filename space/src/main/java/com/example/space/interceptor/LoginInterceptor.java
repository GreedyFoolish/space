package com.example.space.interceptor;

import com.example.space.enums.AuthCode;
import com.example.space.annotation.AuthValidate;
import com.example.space.model.User;
import com.example.space.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<HttpServletRequest> REQUEST_HOLDER = new ThreadLocal<>();

    /**
     * 请求处理前执行
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  处理器
     * @return 是否继续处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 存储当前请求
        REQUEST_HOLDER.set(request);

        // 判断是否为方法请求
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        // 获取注解
        AuthValidate annotation = handlerMethod.getMethodAnnotation(AuthValidate.class);

        if (annotation == null) {
            return true;
        }

        // 模拟获取当前用户
        Object loginUser = request.getSession().getAttribute("loginUser");

        // 获取当前用户
        User user = (User) loginUser;

        // 未登录
        if (user == null) {
            ResponseUtil.writeJson(response, "用户未登录", HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 根据注解值判断是否需要登录
        AuthCode[] requiredRoles = annotation.value();

        // 判断当前用户是否具有权限
        boolean hasPermission = false;

        // 根据注解值判断是否需要登录
        for (AuthCode role : requiredRoles) {
            System.out.println("role " + role);
            if (user.getAuthCode() == role.getId()) {
                hasPermission = true;
                break;
            }
        }

        if (!hasPermission) {
            ResponseUtil.writeJson(response, "权限不足", HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        return true;
    }

    /**
     * 请求处理后执行
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  处理器
     * @param ex       异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        REQUEST_HOLDER.remove();
    }

}
