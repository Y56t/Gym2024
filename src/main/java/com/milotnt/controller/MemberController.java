package com.milotnt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.milotnt.entity.Member;
import com.milotnt.service.IMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    @Autowired
    private IMemberService memberService;
    /**
     * 1.跳转会员卡查询主页面
     * @return
     */
    @RequestMapping("/toSelectByMemberAccount")
    public String toSelectByMemberAccount(){
        return "selectByMemberAccount.html";
    }

    /**
     * 1.主页跳转会员卡查询主页面
     * @return
     */
    @RequestMapping("/toSelByCard")
    public String toSelByCard(){
        return "selectByMemberAccount.html";
    }

    @PostMapping("/selByCard")
    public String selectByMemberAccount(@RequestParam String memberAccount, Model model) {
        // 构造查询条件
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        
        if (memberAccount != null && !memberAccount.trim().isEmpty()) {
            // 精确匹配会员卡号
            queryWrapper.eq("member_account", memberAccount);
        }
        
        // 按办卡时间倒序排序
        queryWrapper.orderByDesc("card_time");
        
        // 查询会员信息
        List<Member> memberList = memberService.list(queryWrapper);
        
        // 将结果添加到 Model
        model.addAttribute("memberList", memberList);
        
        // 返回视图名称
        return "selectByMemberAccount";
    }

}