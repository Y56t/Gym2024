package com.milotnt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-12-23
 */
//@RestController
    @Controller
//@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/toAdminMain")
    public String goToHome() {
        return "adminMain"; // 返回主页的视图名称
    }

}
