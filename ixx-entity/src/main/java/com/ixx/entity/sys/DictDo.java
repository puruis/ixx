package com.ixx.entity.sys;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ixx.common.baseentity.BaseEntity;
import com.ixx.common.enumeration.defaults.DelFlag;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-27
 * Time: 17:47
 */
@Getter
@Setter
@TableName("l_dict")
public class DictDo extends BaseEntity {
    @TableId
    private String id;
    private String name;
    private String value;
    private String type;
    private String description;
    private Integer sort;
    private String parentId;
    @TableField(fill = FieldFill.INSERT)
    private String createUser;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private String remarks;
    @TableField(fill = FieldFill.INSERT)
    private DelFlag delFlag;
}
