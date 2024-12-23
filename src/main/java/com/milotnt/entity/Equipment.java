package com.milotnt.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("equipment")
@ApiModel(value="Equipment对象", description="")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "器材id")
    @TableId(value = "equipment_id", type = IdType.AUTO)
    private Integer equipmentId;

    @ApiModelProperty(value = "器材名称")
    @TableField("equipment_name")
    private String equipmentName;

    @ApiModelProperty(value = "器材位置")
    @TableField("equipment_location")
    private String equipmentLocation;

    @ApiModelProperty(value = "器材状态")
    @TableField("equipment_status")
    private String equipmentStatus;

    @ApiModelProperty(value = "器材备注信息")
    @TableField("equipment_message")
    private String equipmentMessage;


}
