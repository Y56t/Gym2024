package com.milotnt.service.impl;

import com.milotnt.entity.Member;
import com.milotnt.mapper.MemberMapper;
import com.milotnt.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;

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


    private MemberMapper memberMapper;
    @Autowired
    private HttpSession session;
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

    @Override
    public Member getCurrentMember() {
        // 从session中获取登录用户的账号
        String memberAccount = (String) session.getAttribute("memberAccount");
        if (memberAccount == null) {
            throw new RuntimeException("用户未登录");
        }

        // 使用MyBatis-Plus的方法查询
        return memberMapper.selectOne(
                new LambdaQueryWrapper<Member>()
                        .eq(Member::getMemberAccount, memberAccount)
        );
    }
    @Override
    public Member selectByAccount(String memberAccount) {
        return this.lambdaQuery()
                .eq(Member::getMemberAccount, memberAccount)
                .one();
    }

    @Override
    public boolean updateMember(Member member) {
        return this.updateById(member);
    }
}

