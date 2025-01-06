package com.milotnt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.milotnt.entity.ClassOrder;
import com.milotnt.entity.ClassTable;
import com.milotnt.entity.Member;
import com.milotnt.mapper.ClassOrderMapper;
import com.milotnt.service.IClassOrderService;
import com.milotnt.service.IClassTableService;
import com.milotnt.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/user")
public class Reception {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IClassTableService classTableService;
    @Autowired
    private IClassOrderService classOrderService;
    @Autowired
    private ClassOrderMapper classOrderMapper;

    /**
     * 用户主页，显示个人信息
     */
    @GetMapping("/userMain")
    public String userMain(Model model, HttpSession session) {
        try {
            // 从 session 获取登录会员信息
            Member loginMember = (Member) session.getAttribute("user");
            if (loginMember == null) {
                return "redirect:/login";
            }

            // 根据会员账号查询完整信息
            QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("member_account", loginMember.getMemberAccount());
            Member member = memberService.getOne(queryWrapper);

            // 将会员信息添加到 model
            model.addAttribute("member", member);
            return "userMain";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login";
        }
    }

    /**
     * 显示个人信息详情
     */
    @GetMapping("/toUserInfo")
    public String toUserInfo(Model model, HttpSession session) {
        try {
            // 从 session 获取登录会员信息
            Member loginMember = (Member) session.getAttribute("user");
            if (loginMember == null) {
                return "redirect:/login";
            }

            // 使用相同的查询逻辑获取会员信息
            QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("member_account", loginMember.getMemberAccount());
            Member member = memberService.getOne(queryWrapper);

            model.addAttribute("member", member);
            return "userInformation";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "获取个人信息失败");
            return "error";
        }
    }

    /**
     * 跳转到修改个人信息页面
     */
    @GetMapping("/toUpdateInfo")
    public String toUpdateInfo(Model model, HttpSession session) {
        try {
            // 从 session 获取登录会员信息
            Member loginMember = (Member) session.getAttribute("user");
            if (loginMember == null) {
                return "redirect:/login";
            }

            // 查询会员信息
            Member member = memberService.getById(loginMember.getMemberAccount());
            if (member != null) {
                model.addAttribute("member", member);
                return "updateUserInformation";  // 修改这里，与文件名保持一致
            } else {
                model.addAttribute("error", "未找到会员信息");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "获取会员信息失败：" + e.getMessage());
            return "error";
        }
    }

    /**
     * 处理修改个人信息的请求
     */
    @PostMapping("/updateInfo")
    public String updateInfo(Member member, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            Member loginMember = (Member) session.getAttribute("user");
            if (loginMember == null) {
                return "redirect:/login";
            }

            // 设置不能修改的字段
            member.setMemberAccount(loginMember.getMemberAccount());
            member.setMemberPassword(loginMember.getMemberPassword());
            member.setCardTime(loginMember.getCardTime());
            member.setCardNextClass(loginMember.getCardNextClass());

            // 更新会员信息
            boolean success = memberService.updateById(member);
            if (success) {
                // 更新 session 中的会员信息
                session.setAttribute("user", member);
                redirectAttributes.addFlashAttribute("success", "个人信息修改成功！");
            } else {
                redirectAttributes.addFlashAttribute("error", "修改失败，请重试！");
            }

            return "redirect:/user/toUserInfo";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "修改失败：" + e.getMessage());
            return "redirect:/user/toUpdateInfo";
        }
    }
    /**
     * 跳转到课程报名页面
     */
    @GetMapping("/toApplyClass")
    public String toApplyClass(Model model) {
        // 查询所有可报名课程
        List<ClassTable> classList = classTableService.list();
        model.addAttribute("classList", classList);
        return "userApplyClass";
    }

    /**
     * 处理课程报名
     */
    @GetMapping("/applyClass")
    public String applyClass(@RequestParam("classId") Integer classId,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        try {
            // 获取当前登录会员信息
            Member member = (Member) session.getAttribute("user");

            // 获取课程信息
            ClassTable classTable = classTableService.getById(classId);

            // 创建报名记录
            ClassOrder classOrder = new ClassOrder();
            classOrder.setClassId(classId);
            classOrder.setClassName(classTable.getClassName());
            classOrder.setCoach(classTable.getCoach());
            classOrder.setMemberName(member.getMemberName());
            classOrder.setMemberAccount(Integer.parseInt(member.getMemberAccount()));
            classOrder.setClassBegin(classTable.getClassBegin());

            // 保存报名记录
            if (classOrderService.save(classOrder)) {
                // 更新会员剩余课时
                member.setCardNextClass(member.getCardNextClass() - 1);
                memberService.updateById(member);
                redirectAttributes.addFlashAttribute("success", "报名成功");
            } else {
                redirectAttributes.addFlashAttribute("error", "报名失败");
            }

            return "redirect:/user/toUserClass";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "报名失败：" + e.getMessage());
            return "redirect:/user/toApplyClass";
        }
    }

    /**
     * 跳转到我的课程页面
     */
    @GetMapping("/toUserClass")
    public String toUserClass(Model model, HttpSession session) {
        // 获取当前登录会员
        Member member = (Member) session.getAttribute("user");

        // 查询该会员的所有课程订单
        List<ClassOrder> classOrderList = classOrderMapper.selectList(
                new LambdaQueryWrapper<ClassOrder>()
                        .eq(ClassOrder::getMemberAccount, Integer.parseInt(member.getMemberAccount()))
        );

        model.addAttribute("classOrderList", classOrderList);
        return "userClass";
    }

    /**
     * 删除课程订单（退课）
     */
    @GetMapping("/delUserClass")
    public String delUserClass(@RequestParam("classOrderId") Integer classOrderId,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        try {
            // 获取当前登录会员
            Member member = (Member) session.getAttribute("user");

            // 获取要删除的课程订单
            ClassOrder classOrder = classOrderService.getById(classOrderId);

            // 验证是否是当前会员的课程
            if (!classOrder.getMemberAccount().equals(Integer.parseInt(member.getMemberAccount()))) {
                redirectAttributes.addFlashAttribute("error", "无权操作此课程");
                return "redirect:/user/toUserClass";
            }

            // 删除课程订单
            if (classOrderService.removeById(classOrderId)) {
                // 返还课时
                member.setCardNextClass(member.getCardNextClass() + 1);
                memberService.updateById(member);
                redirectAttributes.addFlashAttribute("success", "退课成功");
            } else {
                redirectAttributes.addFlashAttribute("error", "退课失败");
            }

            return "redirect:/user/toUserClass";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "退课失败：" + e.getMessage());
            return "redirect:/user/toUserClass";
        }
    }
    /**
     * 用户主页
     */

}
