package com.ixx.entity.sys;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ixx.common.baseentity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:21
 */
@Getter
@Setter
@TableName("l_menu")
public class MenuDo extends BaseEntity{
    @TableId
    private String id;
    private String pid;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 跳转链接
     */
    private String menuUrl;
    /**
     * 目录类型
     */
    private Integer type;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer orders;
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
