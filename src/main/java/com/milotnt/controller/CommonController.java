package com.milotnt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/toUserLogin")
    public String toUserLogin() {
        return "redirect:/userLogin";
    }

    @GetMapping("/toAdminLogin")
    public String toAdminLogin() {
        return "redirect:/adminLogin";
    }
}