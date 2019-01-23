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
 * Time: 17:28
 */
@Getter
@Setter
@TableName("l_user_to_role")
public class UserToRoleDo extends BaseEntity{
    @TableId
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 是否启用:0:启用 1:正常
     */
    @TableField(fill = FieldFill.INSERT)
    private DelFlag delFlag;
    /**
     *  创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT)
    private String createUser;
    /**
     *  修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;
}
