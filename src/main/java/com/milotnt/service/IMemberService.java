package com.milotnt.service;

import com.milotnt.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-12-23
 */
public interface IMemberService extends IService<Member> {

    Member getCurrentMember();
    // 根据账号查询会员
    Member selectByAccount(String memberAccount);

    // 更新会员信息
    boolean updateMember(Member member);
}
