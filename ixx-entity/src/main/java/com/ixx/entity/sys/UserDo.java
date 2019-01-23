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
 * Date: 2018-11-12
 * Time: 16:26
 */
@Getter
@Setter
@TableName("l_user")
public class UserDo extends BaseEntity{
    @TableId
    private String id;
    /**
     *  用户名称
     */
    private String userName;
    /**
     *  用户昵称
     */
    private String nickName;
    /**
     *  密码
     */
    private String passWord;
    /**
     *  用户头像
     */
    private String photo;
    /**
     *  用户邮箱
     */
    private String email;
    /**
     *  用户状态
     */
    private DelFlag delFlag;
    /**
     *  用户最后一次登录时间
     */
    private Date lastLoginTime;
    /**
     *  用户登录次数
     */
    private Integer loginCount;
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
