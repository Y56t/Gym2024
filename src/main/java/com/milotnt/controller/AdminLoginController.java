package com.milotnt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milotnt.entity.Admin;
import com.milotnt.mapper.AdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class AdminLoginController {

    @Autowired
    private AdminMapper adminMapper;

    // 显示管理员登录页面
    @GetMapping({"/", "/adminLogin"})
    public String showAdminLoginPage() {
        return "adminLogin";
    }

    // 处理管理员登录请求
    @PostMapping("/adminLogin")
    public String adminLogin(Admin admin, Model model, HttpSession session) {
        try {
            log.info("开始执行管理员登录查询，账号：{}", admin.getAdminAccount());

            LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Admin::getAdminAccount, admin.getAdminAccount())
                    .eq(Admin::getAdminPassword, admin.getAdminPassword());

            Admin loginAdmin = adminMapper.selectOne(queryWrapper);

            if (loginAdmin != null) {
                log.info("管理员登录成功：{}", loginAdmin.getAdminAccount());
                session.setAttribute("admin", loginAdmin);
                return "redirect:/adminMain";
            }

            log.warn("管理员登录失败，账号或密码错误：{}", admin.getAdminAccount());
            model.addAttribute("msg", "账号或密码错误！");
            return "adminLogin";

        } catch (Exception e) {
            log.error("管理员登录过程发生错误: ", e);
            model.addAttribute("msg", "系统错误，请稍后重试！");
            return "adminLogin";
        }
    }

    // 管理员主页
    @GetMapping("/adminMain")
    public String adminMain(HttpSession session, Model model) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/adminLogin";
        }
        return "adminMain";
    }

    // 管理员退出登录
    @GetMapping("/adminLogout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/adminLogin";
    }
}