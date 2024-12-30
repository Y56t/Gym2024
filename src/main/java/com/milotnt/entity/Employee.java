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
    private String entryTime;

    @ApiModelProperty(value = "职务")
    @TableField("staff")
    private String staff;

    @ApiModelProperty(value = "备注信息")
    @TableField("employee_message")
    private String employeeMessage;

    public Employee() {
    }

    public Employee(Integer employeeAccount, String employeeName, String employeeGender, Integer employeeAge, String entryTime, String staff, String employeeMessage) {
        this.employeeAccount = employeeAccount;
        this.employeeName = employeeName;
        this.employeeGender = employeeGender;
        this.employeeAge = employeeAge;
        this.entryTime = entryTime;
        this.staff = staff;
        this.employeeMessage = employeeMessage;
    }


    public Integer getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(Integer employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public String getEmployeeName() {
        return employeeName;
    }

//    public void setEmployeeName(String employeeName) {
//        this.employeeName = employeeName;
//    }

    public String getEmployeeGender() {
        return employeeGender;
    }

//    public void setEmployeeGender(String employeeGender) {
//        this.employeeGender = employeeGender;
//    }

    public Integer getEmployeeAge() {
        return employeeAge;
    }

//    public void setEmployeeAge(Integer employeeAge) {
//        this.employeeAge = employeeAge;
//    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getStaff() {
        return staff;
    }

//    public void setStaff(String staff) {
//        this.staff = staff;
//    }

    public String getEmployeeMessage() {
        return employeeMessage;
    }

//    public void setEmployeeMessage(String employeeMessage) {
//        this.employeeMessage = employeeMessage;
//    }

    @Override
    public String toString() {
        return "Employee{" +
                ", employeeAccount=" + employeeAccount +
                ", employeeName='" + employeeName + '\'' +
                ", employeeGender='" + employeeGender + '\'' +
                ", employeeAge=" + employeeAge +
                ", entryTime='" + entryTime + '\'' +
                ", staff='" + staff + '\'' +
                ", employeeMessage='" + employeeMessage + '\'' +
                '}';
    }
}









































































