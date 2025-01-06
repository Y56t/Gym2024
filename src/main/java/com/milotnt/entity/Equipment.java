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
    @TableId(type = IdType.INPUT)
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

    public Equipment() {
    }

    public Equipment(Integer equipmentId, String equipmentName, String equipmentLocation, String equipmentStatus, String equipmentMessage) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentLocation = equipmentLocation;
        this.equipmentStatus = equipmentStatus;
        this.equipmentMessage = equipmentMessage;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentLocation() {
        return equipmentLocation;
    }

    public void setEquipmentLocation(String equipmentLocation) {
        this.equipmentLocation = equipmentLocation;
    }

    public String getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public String getEquipmentMessage() {
        return equipmentMessage;
    }

    public void setEquipmentMessage(String equipmentMessage) {
        this.equipmentMessage = equipmentMessage;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", equipmentName='" + equipmentName + '\'' +
                ", equipmentLocation='" + equipmentLocation + '\'' +
                ", equipmentStatus='" + equipmentStatus + '\'' +
                ", equipmentMessage='" + equipmentMessage + '\'' +
                '}';
    }
}
