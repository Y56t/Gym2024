package com.milotnt.service.impl;

import com.milotnt.entity.Equipment;
import com.milotnt.mapper.EquipmentMapper;
import com.milotnt.service.IEquipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-12-23
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {
    private static final int START_ID = 101000001;

    @Autowired
    private EquipmentMapper equipmentMapper;

    //添加器材
    @Override
    public Boolean add(Equipment equipment) {
        // 如果是新增，使用 START_ID
        if (equipment.getEquipmentId() == null) {
            Integer maxId = equipmentMapper.selectMaxId();
            equipment.setEquipmentId(maxId == null ? START_ID : Math.max(maxId + 1, START_ID));
        }
        return equipmentMapper.add(equipment);
    }

    //根据id删除器械
    @Override
    public Boolean delete(Integer equipmentId) {
        return equipmentMapper.delete(equipmentId);
    }

    //根据id修改器械信息
    @Override
    public Boolean update(Equipment equipment) {
        return equipmentMapper.update(equipment);
    }

    //查询所有器械
    @Override
    public List<Equipment> findAll() {
        return equipmentMapper.findAll();
    }

    @Override
    public List<Equipment> selectByEquipmentId(Integer equipmentId) {
        return equipmentMapper.selectByEquipmentId(equipmentId);
    }

    @Override
    public void resetAutoIncrement() {

    }


}
