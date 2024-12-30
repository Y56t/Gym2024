package com.milotnt.service.impl;

import com.milotnt.entity.Member;
import com.milotnt.mapper.MemberMapper;
import com.milotnt.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;

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
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Override
    public Member userLogin(Member member) {
        try {
            log.info("开始执行登录查询，账号：{}", member.getMemberAccount());

            LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Member::getMemberAccount, member.getMemberAccount())
                    .eq(Member::getMemberPassword, member.getMemberPassword());

            return this.getOne(queryWrapper);
        } catch (Exception e) {
            log.error("登录查询发生错误: ", e);
            throw e;
        }
    }
}
