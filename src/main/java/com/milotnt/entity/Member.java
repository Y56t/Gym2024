package com.milotnt.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import nonapi.io.github.classgraph.json.Id;

/**
 * <p>
 *
 * </p >
 *
 * @author author
 * @since 2024-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("member")
@ApiModel(value="Member对象", description="")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员账号")
    @TableId(value = "member_account", type = IdType.AUTO)
    private String memberAccount;

    @ApiModelProperty(value = "会员密码")
    @TableField("member_password")
    private String memberPassword;

    @ApiModelProperty(value = "会员姓名")
    @TableField("member_name")
    private String memberName;

    @ApiModelProperty(value = "会员性别")
    @TableField("member_gender")
    private String memberGender;

    @ApiModelProperty(value = "会员年龄")
    @TableField("member_age")
    private Integer memberAge;

    @ApiModelProperty(value = "会员身高")
    @TableField("member_height")
    private Integer memberHeight;

    @ApiModelProperty(value = "会员体重")
    @TableField("member_weight")
    private Integer memberWeight;

    @ApiModelProperty(value = "会员电话")
    @TableField("member_phone")
    private Long memberPhone;

    @ApiModelProperty(value = "办卡时间")
    @TableField("card_time")
    private LocalDate cardTime;

    @ApiModelProperty(value = "购买课时")
    @TableField("card_class")
    private Integer cardClass;

    @ApiModelProperty(value = "剩余课时")
    @TableField("card_next_class")
    private Integer cardNextClass;
}