package com.milotnt.controller;

import com.milotnt.entity.Member;
import com.milotnt.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class Reception {

    @Autowired
    private IMemberService memberService;

    // 跳转到个人信息页面
    @GetMapping("/toUserInfo")
    public String toUserInfo(Model model, HttpSession session) {
        String memberAccount = (String) session.getAttribute("memberAccount");
        Member member = memberService.selectByAccount(memberAccount);
        model.addAttribute("member", member);
        return "userInformation";
    }

    // 跳转到修改个人信息页面
    @GetMapping("/toUpdateInfo")
    public String toUpdateInfo(Model model, HttpSession session) {
        String memberAccount = (String) session.getAttribute("memberAccount");
        Member member = memberService.selectByAccount(memberAccount);
        model.addAttribute("member", member);
        return "updateUserInformation";
    }

    // 处理更新个人信息的请求
    @PostMapping("/updateInfo")
    public String updateInfo(Member member) {
        if (memberService.updateMember(member)) {
            return "redirect:/user/toUserInfo";
        }
        return "error";
    }
    /**
     * 跳转到报名选课页面
     */


}
