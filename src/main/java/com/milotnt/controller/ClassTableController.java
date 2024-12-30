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
@Controller
@RequestMapping("/class")
public class ClassTableController {




/**
 * 跳转课程管理界面
 */


    @GetMapping("/selClass")
    public String toClassManagement() {
        // 跳转到课程管理页面
        return "/selectClass";
    }
/**
 * 跳转新增
 */
    @GetMapping("/toAddClass")
    public String toaddClass() {
        // 跳转到课程管理页面
        return "/addClass";
}
/**
 * 新增
 */
}



