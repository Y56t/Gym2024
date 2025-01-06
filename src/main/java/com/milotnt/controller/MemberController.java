package com.milotnt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.milotnt.entity.Member;
import com.milotnt.service.IMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

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
        return "selectByMemberAccount";
    }

    /**
     * 2.主页跳转会员卡查询主页面
     * @return
     */
    @RequestMapping("/toSelByCard")
    public String toSelByCard(){
        return "selectByMemberAccount";
    }

    /**
     * 3.查询功能
     * @return
     */
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
    /**
     * 4.转到编辑页面
     * @return
     */
    @RequestMapping("/toUpdateMember")
    public String toUpdateMember(@RequestParam String memberAccount, Model model){
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
        return "UpdateMember";
    }

    /**
     * 5.编辑功能
     * @return
     */
    @RequestMapping("/updateMember")
    public String updateMember(@ModelAttribute Member member,String memberAccount,Model model){
        UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("member_account", member.getMemberAccount());
        memberService.update(member, updateWrapper);
        model.addAttribute("message", "修改成功！");

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

        return "UpdateMember";
    }


    /**
     * 5.主页跳转会员管理
     * @return
     */
    @RequestMapping("/selMember")
    public String selMember (Model model){
        // 构造查询条件
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
//        // 按办卡时间倒序排序
        queryWrapper.orderByDesc("card_time");

//         查询会员信息
        List<Member> memberList = memberService.list(queryWrapper);
        // 将结果添加到 Model
        model.addAttribute("memberList", memberList);
        return "selectMember";
    }

    /**
     * 2.会员管理跳转添加会员页面
     * @return
     */
    @GetMapping("/toAddMember")
    public String toAddMember(Model model){
        String generatedMemberAccount = generateMemberAccount(); // 生成会员账号的逻辑
        model.addAttribute("generatedMemberAccount", generatedMemberAccount);
        return "addMember";
    }

    // 生成会员账号的逻辑
    public String generateMemberAccount() {
        Random random = new Random();
    int randomNumber = random.nextInt(1000000000); // 生成一个 0 到 999999999 的随机数
    return String.format("%09d", randomNumber); // 确保返回的字符串是九位数，不足的前面补零
    }

    /**
     * 2.添加会员功能
     * @return
     */
    @RequestMapping("/addMember")
    public String addMember( @ModelAttribute Member member,Model model){
        System.out.println(member);
        String generatedMemberAccount = generateMemberAccount(); // 生成会员账号的逻辑
        member.setMemberAccount(Integer.valueOf(generatedMemberAccount));
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        member.setCardTime(currentDate);
        member.setCardNextClass(member.getCardClass());
//        int remainingCardTime = currentDate - member.getCardTime();
        memberService.save(member);
        model.addAttribute("message", "添加成功！");
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        // 按办卡时间倒序排序
        queryWrapper.orderByDesc("card_time");

        // 查询会员信息
        List<Member> memberList = memberService.list(queryWrapper);
        System.out.println(memberList);

        // 将结果添加到 Model
        model.addAttribute("memberList", memberList);
        return "addMember";
    }


    @RequestMapping("delMember")
    public String delMember(@RequestParam String memberAccount, Model model , RedirectAttributes redirectAttributes){
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        if (memberAccount != null && !memberAccount.trim().isEmpty()) {
            // 精确匹配会员卡号
            queryWrapper.eq("member_account", memberAccount);
        }
        boolean remove = memberService.remove(queryWrapper);
        //设置删除成功的消息
        if (remove) {
            redirectAttributes.addFlashAttribute("message", "删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("message", "删除失败！");
        }
        List<Member> memberList = memberService.list(queryWrapper);
        System.out.println(memberList);

        // 将结果添加到 Model
        model.addAttribute("memberList", memberList);

        return "redirect:/member/selMember";
    }

}
