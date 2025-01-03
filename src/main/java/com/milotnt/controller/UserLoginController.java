package com.milotnt.controller;

import com.milotnt.entity.Member;
import com.milotnt.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class UserLoginController {

    @Autowired
    private IMemberService memberService;

    @GetMapping("/userLogin")
    public String showLoginPage() {
        return "userLogin";
    }

    // 处理会员登录请求
    @PostMapping("/userLogin")
    public String userLogin(Member member, Model model, HttpSession session) {
        log.info("接收到会员登录请求，账号：{}", member.getMemberAccount());

        try {
            Member loginMember = memberService.userLogin(member);
            if (loginMember != null) {
                log.info("会员登录成功：{}", loginMember.getMemberAccount());
                model.addAttribute("member", loginMember);
                session.setAttribute("user", loginMember);
                return "redirect:/userMain";  // 使用重定向避免表单重复提交
            }

            log.warn("会员登录失败，账号或密码错误：{}", member.getMemberAccount());
            model.addAttribute("msg", "您输入的账号或密码有误，请重新输入!");
            return "userLogin";

        } catch (Exception e) {
            log.error("会员登录过程发生错误: ", e);
            model.addAttribute("msg", "系统错误，请稍后重试!");
            return "userLogin";
        }
    }

    // 会员主页
    @GetMapping("/userMain")
    public String userMain(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("user");
        if (member == null) {
            return "redirect:/userLogin";
        }
        model.addAttribute("member", member);
        return "userMain";
    }

    // 会员退出登录
    @GetMapping("/userLogout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/userLogin";
    }
}
