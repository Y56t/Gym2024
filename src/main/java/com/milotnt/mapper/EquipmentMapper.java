package com.milotnt.mapper;

import com.milotnt.entity.Equipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-12-23
 */
public interface EquipmentMapper extends BaseMapper<Equipment> {
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

    Integer selectMaxId();
}
