package com.ixx.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ixx.common.baseentity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Description:登录记录
 * User: purui_zhang
 * Date: 2018-12-21
 * Time: 12:02
 */
@Getter
@Setter
@TableName("l_login_notes")
public class LoginNoticeDo  extends BaseEntity{
    private String id;
    private String userName;
    private Date loginTime;
    private Date logoutTime;
    private String ip;
    private String address;
    private String operatingSys;
    private String browser;
    private String rsv1;
    private String rsv2;
    private String rsv3;
    private String rsv4;
    private String rsv5;
    @TableField(exist = false)
    private UserDo userDo;
}
