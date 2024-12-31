package com.milotnt.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milotnt.entity.ClassOrder;
import com.milotnt.entity.ClassTable;
import com.milotnt.entity.Employee;
import com.milotnt.entity.Member;
import com.milotnt.mapper.ClassOrderMapper;
import com.milotnt.service.IClassOrderService;
import com.milotnt.service.IMemberService;
import com.milotnt.utils.PageHelper;
import org.springframework.ui.Model;

import com.milotnt.service.IClassTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private IMemberService iMemberService;  // 添加会员服务
    @Autowired
    private IClassOrderService classOrderService;
    @Autowired
    private ClassOrderMapper classOrderMapper;
/**
 * 跳转课程管理界面
 */


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

@GetMapping("/toUserInfo")
public String toUserInformation(Model model) {
    // 需要从数据库或其他地方获取会员信息
    Member member = iMemberService.getCurrentMember();  // 假设有这样的服务方法
    model.addAttribute("member", member);
    return "userInformation";
}


    @PostMapping("/addClass")
    public String addEmployee(@ModelAttribute ClassTable classTable, RedirectAttributes redirectAttributes) {
        try {

            SecureRandom secureRandom = new SecureRandom();
            String account = String.format("1010%05d", secureRandom.nextInt(100000));
            classTable.setClassId(Integer.parseInt(account));


            classTable.setClassTime(LocalDate.now().format(DateTimeFormatter.ISO_DATE));

            Boolean success = classService.save(classTable);
            if (success) {
                redirectAttributes.addFlashAttribute("success", "课程添加成功");
            } else {
                redirectAttributes.addFlashAttribute("error", "课程添加失败");
            }
            return "redirect:/class/selClass";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "添加失败：" + e.getMessage());
            return "redirect:/class/toAddClass";
        }
    }


    @GetMapping("/selClass")
    public String list(Model model) {
        // 查询所有课程信息
        List<ClassTable> classList = classService.list();
        model.addAttribute("classList", classList);
        return "selectClass";
    }



    @GetMapping("/selClassOrder")
    public String selectClassOrder(@RequestParam("classId") Integer classId, Model model) {
        // 直接使用Mapper查询
        List<ClassOrder> classOrderList = classOrderMapper.selectList(
                new LambdaQueryWrapper<ClassOrder>()
                        .eq(ClassOrder::getClassId, classId)
        );

        model.addAttribute("classOrderList", classOrderList);
        return "selectClassOrder";
    }

}



