package com.milotnt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("class_order")
@ApiModel(value="ClassOrder对象", description="")
public class ClassOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "报名表id")
    @TableId(value = "class_order_id", type = IdType.AUTO)
    private Integer classOrderId;

    @ApiModelProperty(value = "课程id")
    @TableField("class_id")
    private Integer classId;

    @ApiModelProperty(value = "课程名称")
    @TableField("class_name")
    private String className;

    @ApiModelProperty(value = "教练")
    @TableField("coach")
    private String coach;

    @ApiModelProperty(value = "会员姓名")
    @TableField("member_name")
    private String memberName;

    @ApiModelProperty(value = "会员账号")
    @TableField("member_account")
    private Integer memberAccount;

    @ApiModelProperty(value = "开课时间")
    @TableField("class_begin")
    private String classBegin;


}
