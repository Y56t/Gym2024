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

    //主页、跳转管理员登录页面
    @RequestMapping("/")
    public String toAdminLogin() {
        return "adminLogin";
    }

    //跳转会员登录页面
    @RequestMapping("/toUserLogin")
    public String toUserLogin() {
        return "userLogin";
    }
    @RequestMapping("/toadminMain")
    public String toadminMain() {
        return "adminMain";
    }
}
