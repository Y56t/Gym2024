package com.milotnt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("member")
public class Member {
    @TableId
    private String memberAccount;    // 会员账号
    private String memberName;       // 姓名
    private String memberPassword;   // 密码
    private String memberGender;     // 性别
    private Integer memberAge;       // 年龄
    private String memberPhone;      // 联系方式
    private Double memberHeight;     // 身高
    private Double memberWeight;     // 体重
    private String cardTime;         // 办卡时间
    private Integer cardNextClass;   // 剩余课时
}
