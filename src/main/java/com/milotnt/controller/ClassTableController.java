package com.milotnt.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milotnt.entity.ClassTable;
import com.milotnt.utils.PageHelper;
import org.springframework.ui.Model;

import com.milotnt.service.IClassTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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


    @Autowired
    private IClassTableService classService;

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
        // 跳转到新增管理页面
        return "/addClass";
}
/**
 * 新增
 */



}



