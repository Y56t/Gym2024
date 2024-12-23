package com.milotnt.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 李某人
 * @Date: 2024/11/12/22:41
 * @Description:通用的控制器来跳转页面
 */
@Controller
public class CommonController {

    /**
     * 1.跳转用户页面
     * @return
     */
    @RequestMapping("/login")
    public String toLoginPage(){
        return "userLogin.html";
    }

    /**
     * 1.跳转管理员登录页面
     * @return
     */
    @RequestMapping("/adminLogin")
    public String toAdminLogin(){
        return "adminLogin.html";
    }

    /**
     * 1.跳转管理员主页面
     * @return
     */
    @RequestMapping("/adminMain")
    public String toAdminMain(){
        return "adminMain.html";
    }






}
