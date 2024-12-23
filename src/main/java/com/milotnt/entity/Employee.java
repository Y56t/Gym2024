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
@TableName("employee")
@ApiModel(value="Employee对象", description="")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工账号")
    @TableId(value = "employee_account", type = IdType.AUTO)
    private Integer employeeAccount;

    @ApiModelProperty(value = "员工姓名")
    @TableField("employee_name")
    private String employeeName;

    @ApiModelProperty(value = "员工性别")
    @TableField("employee_gender")
    private String employeeGender;

    @ApiModelProperty(value = "员工年龄")
    @TableField("employee_age")
    private Integer employeeAge;

    @ApiModelProperty(value = "入职时间")
    @TableField("entry_time")
    private LocalDate entryTime;

    @ApiModelProperty(value = "职务")
    @TableField("staff")
    private String staff;

    @ApiModelProperty(value = "备注信息")
    @TableField("employee_message")
    private String employeeMessage;


}
