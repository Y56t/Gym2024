package com.milotnt.service;

import com.milotnt.entity.Equipment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-12-23
 */
public interface IEquipmentService extends IService<Equipment> {
    //添加器材
    Boolean add(Equipment equipment);

    //根据id删除器械
    Boolean delete(Integer equipmentId);

    //根据id修改器械信息
    Boolean update(Equipment equipment);

    //查询所有器械
    List<Equipment> findAll();

    //根据id查询器械
    List<Equipment> selectByEquipmentId(Integer equipmentId);

    @Update("ALTER TABLE equipment AUTO_INCREMENT = (SELECT max_id FROM (SELECT MAX(equipment_id) + 1 as max_id FROM equipment) AS temp)")
    void resetAutoIncrement();
}
