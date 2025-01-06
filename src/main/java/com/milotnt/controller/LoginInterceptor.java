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

        // 登录相关路径放行
        if(requestURI.contains("/userLogin") ||
                requestURI.contains("/adminLogin") ||
                requestURI.contains("/css/") ||
                requestURI.contains("/js/") ||
                requestURI.contains("/img/")) {
            return true;
        }

        // 检查登录状态,区分管理员和用户
        if(requestURI.startsWith("/admin")) {
            Object admin = request.getSession().getAttribute("admin");
            if(admin == null) {
                response.sendRedirect("/adminLogin");
                return false;
            }
        } else {
            Object user = request.getSession().getAttribute("user");
            if(user == null) {
                response.sendRedirect("/userLogin");
                return false;
            }
        }
        return true;
    }
}
