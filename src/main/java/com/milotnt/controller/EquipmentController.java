package com.milotnt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.milotnt.entity.Equipment;
import com.milotnt.mapper.EquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentMapper equipmentMapper;

    // 查询所有器材
    @GetMapping("/selEquipment")
    public String selectEquipment(Model model) {
        List<Equipment> equipmentList = equipmentMapper.selectList(null);
        model.addAttribute("equipmentList", equipmentList);
        return "selectEquipment";
    }

    // 跳转到添加器材页面
    @GetMapping("/toAddEquipment")
    public String toAddEquipment() {
        return "addEquipment";
    }

    // 处理添加器材请求
    @PostMapping("/addEquipment")
    public String addEquipment(@ModelAttribute Equipment equipment, RedirectAttributes redirectAttributes) {
        try {
            // 查询最大的 equipmentId
            Equipment maxIdEquipment = equipmentMapper.selectOne(
                    new QueryWrapper<Equipment>()
                            .orderByDesc("equipment_id")
                            .last("limit 1")
            );

            // 设置新的 equipmentId
            int newId;
            if (maxIdEquipment == null) {
                // 如果没有记录，从 10100001 开始
                newId = 10100001;
            } else {
                // 否则在最大ID基础上加1
                newId = maxIdEquipment.getEquipmentId() + 1;
            }
            equipment.setEquipmentId(newId);

            int rows = equipmentMapper.insert(equipment);
            if (rows > 0) {
                redirectAttributes.addFlashAttribute("success", "器材添加成功");
            } else {
                redirectAttributes.addFlashAttribute("error", "器材添加失败");
            }
            return "redirect:/equipment/selEquipment";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "添加失败：" + e.getMessage());
            return "redirect:/equipment/toAddEquipment";
        }
    }

    // 跳转到编辑器材页面
    @GetMapping("/toUpdateEquipment")
    public String toUpdateEquipment(@RequestParam Integer equipmentId, Model model) {
        Equipment equipment = equipmentMapper.selectOne(
                new LambdaQueryWrapper<Equipment>()
                        .eq(Equipment::getEquipmentId, equipmentId)
        );
        model.addAttribute("equipmentList", List.of(equipment));
        return "updateEquipment";
    }

    // 处理更新器材请求
    @PostMapping("/updateEquipment")
    public String updateEquipment(@ModelAttribute Equipment equipment, RedirectAttributes redirectAttributes) {
        try {
            int rows = equipmentMapper.update(equipment,
                    new LambdaQueryWrapper<Equipment>()
                            .eq(Equipment::getEquipmentId, equipment.getEquipmentId())
            );
            if (rows > 0) {
                redirectAttributes.addFlashAttribute("success", "器材信息更新成功");
            } else {
                redirectAttributes.addFlashAttribute("error", "器材信息更新失败");
            }
            return "redirect:/equipment/selEquipment";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "更新失败：" + e.getMessage());
            return "redirect:/equipment/toUpdateEquipment?equipmentId=" + equipment.getEquipmentId();
        }
    }

    // 处理删除器材请求
    @GetMapping("/delEquipment")
    public String deleteEquipment(@RequestParam Integer equipmentId) {
        equipmentMapper.delete(
                new LambdaQueryWrapper<Equipment>()
                        .eq(Equipment::getEquipmentId, equipmentId)
        );
        return "redirect:/equipment/selEquipment";
    }
}
