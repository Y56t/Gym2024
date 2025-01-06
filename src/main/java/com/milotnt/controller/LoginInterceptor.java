package com.milotnt.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        // 1. 静态资源和登录相关路径直接放行
        if (requestURI.contains("/css/") ||
                requestURI.contains("/js/") ||
                requestURI.contains("/img/") ||
                requestURI.equals("/") ||
                requestURI.equals("/userLogin") ||
                requestURI.equals("/adminLogin") ||
                requestURI.equals("/toUserLogin") ||
                requestURI.equals("/toAdminLogin")) {
            return true;
        }

        // 2. 获取session中的登录信息
        Object admin = request.getSession().getAttribute("admin");
        Object user = request.getSession().getAttribute("user");

        // 3. 管理员相关路径判断
        if (requestURI.startsWith("/admin")) {
            if (admin != null) {
                return true;
            }
            response.sendRedirect("/adminLogin");
            return false;
        }

        // 4. 用户相关路径判断
        if (requestURI.startsWith("/user")) {
            if (user != null) {
                return true;
            }
            response.sendRedirect("/userLogin");
            return false;
        }

        // 5. 其他路径根据登录状态判断
        if (admin != null || user != null) {
            return true;
        }

        // 6. 未登录则默认跳转到管理员登录页
        response.sendRedirect("/adminLogin");
        return false;
    }
}
