package com.milotnt.controller;



import com.milotnt.entity.Equipment;
import com.milotnt.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    private IEquipmentService equipmentService;

    //查询
    @GetMapping("/selEquipment")
    public String selectEquipment(Model model) {
        List<Equipment> equipment = equipmentService.findAll();
        model.addAttribute("equipmentList", equipment);
        return "selectEquipment";
    }

    // 跳转到添加器材页面
    @GetMapping("/toAddEquipment")
    public String toAddEquipment() {
        return "addEquipment";
    }

    //新增器材
    @RequestMapping("/addEquipment")
    public String addEquipment(Equipment equipment) {
        equipmentService.add(equipment);
        return "redirect:selEquipment";
    }

    //删除器材
    @RequestMapping("/delEquipment")
    public String deleteEquipment(Integer equipmentId) {
        equipmentService.delete(equipmentId);
        return "redirect:selEquipment";
    }

    //跳转修改器材页面
    @RequestMapping("/toUpdateEquipment")
    public String toUpdateEquipment(Integer equipmentId, Model model) {
        List<Equipment> equipmentList = equipmentService.selectByEquipmentId(equipmentId);
        model.addAttribute("equipmentList", equipmentList);
        return "updateEquipment";
    }

    //修改器材
    @RequestMapping("/updateEquipment")
    public String updateEquipment(Equipment equipment) {
        equipmentService.update(equipment);
        return "redirect:selEquipment";
    }


}
