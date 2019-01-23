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
 * Date: 2018-11-13
 * Time: 17:16
 */
@Getter
@Setter
@TableName("l_role")
public class RoleDo extends BaseEntity{
    @TableId
    private String id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色备注
     */
    private String  roleRemk;
    /**
     * 角色创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String  createUser;
    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String  updateUser;
    /**
     *  创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     *  修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT)
    private DelFlag delFlag;
}
