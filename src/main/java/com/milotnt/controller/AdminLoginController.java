package com.milotnt.controller;

import com.milotnt.entity.Admin;
import com.milotnt.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController {
    @Autowired
    private IAdminService adminService;

    // 显示管理员登录页面
    @GetMapping({"/", "/adminLogin"})
    public String showAdminLoginPage() {
        return "adminLogin";
    }

    // 处理管理员登录请求
    @PostMapping("/adminLogin")
    public String adminLogin(Admin admin, Model model, HttpSession session) {
        try {
            Admin loginAdmin = adminService.adminLogin(admin);
            if (loginAdmin != null) {
                session.setAttribute("admin", admin);
                return "redirect:/adminMain";
            }
            model.addAttribute("msg", "账号或密码错误！");
            return "adminLogin";
        } catch (Exception e) {
            model.addAttribute("msg", "系统错误，请稍后重试！");
            return "adminLogin";
        }
    }

    // 管理员主页
    @GetMapping("/adminMain")
    public String adminMain(HttpSession session) {
        if (session.getAttribute("admin") == null) {
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