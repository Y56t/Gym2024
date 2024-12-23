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
@TableName("class_table")
@ApiModel(value="ClassTable对象", description="")
public class ClassTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程id")
    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    @ApiModelProperty(value = "课程名称")
    @TableField("class_name")
    private String className;

    @ApiModelProperty(value = "开课时间")
    @TableField("class_begin")
    private String classBegin;

    @ApiModelProperty(value = "课程时长")
    @TableField("class_time")
    private String classTime;

    @ApiModelProperty(value = "教练")
    @TableField("coach")
    private String coach;


}
