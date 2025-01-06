package com.milotnt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milotnt.entity.Employee;
import com.milotnt.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    // 查询所有员工
    @GetMapping("/selEmployee")
    public String selectEmployee(Model model) {
        List<Employee> employees = employeeMapper.selectList(null);
        model.addAttribute("employeeList", employees);
        return "selectEmployee";
    }

    // 跳转到添加员工页面
    @GetMapping("/toAddEmployee")
    public String toAddEmployee() {
        return "addEmployee";
    }

    // 处理添加员工请求
    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
        try {
            // 生成工号
            SecureRandom secureRandom = new SecureRandom();
            String account = String.format("1010%05d", secureRandom.nextInt(100000));
            employee.setEmployeeAccount(Integer.parseInt(account));

            // 设置入职时间
            employee.setEntryTime(LocalDate.now().format(DateTimeFormatter.ISO_DATE));

            int rows = employeeMapper.insert(employee);
            if (rows > 0) {
                redirectAttributes.addFlashAttribute("success", "员工添加成功");
            } else {
                redirectAttributes.addFlashAttribute("error", "员工添加失败");
            }
            return "redirect:/employee/selEmployee";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "添加失败：" + e.getMessage());
            return "redirect:/employee/toAddEmployee";
        }
    }

    // 跳转到编辑员工页面
    @GetMapping("/toUpdateEmployee")
    public String toUpdateEmployee(@RequestParam Integer employeeAccount, Model model) {
        Employee employee = employeeMapper.selectOne(
                new LambdaQueryWrapper<Employee>()
                        .eq(Employee::getEmployeeAccount, employeeAccount)
        );
        model.addAttribute("employeeList", List.of(employee));
        return "updateEmployee";
    }

    // 处理更新员工请求
    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
        try {
            int rows = employeeMapper.update(employee,
                    new LambdaQueryWrapper<Employee>()
                            .eq(Employee::getEmployeeAccount, employee.getEmployeeAccount())
            );
            if (rows > 0) {
                redirectAttributes.addFlashAttribute("success", "员工信息更新成功");
            } else {
                redirectAttributes.addFlashAttribute("error", "员工信息更新失败");
            }
            return "redirect:/employee/selEmployee";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "更新失败：" + e.getMessage());
            return "redirect:/employee/toUpdateEmployee?employeeAccount=" + employee.getEmployeeAccount();
        }
    }

    // 删除员工
    @GetMapping("/delEmployee")
    public String deleteEmployee(@RequestParam Integer employeeAccount) {
        employeeMapper.delete(
                new LambdaQueryWrapper<Employee>()
                        .eq(Employee::getEmployeeAccount, employeeAccount)
        );
        return "redirect:/employee/selEmployee";
    }
}