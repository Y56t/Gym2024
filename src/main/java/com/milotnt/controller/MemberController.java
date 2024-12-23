package com.milotnt.controller;


import cn.hutool.core.bean.BeanUtil;
import com.milotnt.service.IMemberService;
import com.milotnt.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-12-23
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {


}
