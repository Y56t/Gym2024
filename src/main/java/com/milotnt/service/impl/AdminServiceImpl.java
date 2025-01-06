package com.milotnt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milotnt.entity.Admin;
import com.milotnt.entity.Member;
import com.milotnt.mapper.AdminMapper;
import com.milotnt.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-12-23
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Override
    public Admin adminLogin(Admin admin) {
        try {
            log.info("开始执行登录查询，账号：{}",admin.getAdminAccount());

            LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Admin::getAdminAccount, admin.getAdminAccount())
                    .eq(Admin::getAdminPassword, admin.getAdminPassword());

            return this.getOne(queryWrapper);
        } catch (Exception e) {
            log.error("登录查询发生错误: ", e);
            throw e;
        }
    }
}
