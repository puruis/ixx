package com.ixx.conf.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ixx.common.enumeration.defaults.DelFlag;
import com.ixx.entity.sys.UserDo;
import com.ixx.service.MenuService;
import com.ixx.service.UserService;
import com.ixx.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 16:09
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 授权用户权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /**
         *  从 spring  上下文 中获取 防止 多线程环境出问题
         */
        MenuService menuService = SpringContextHolder.getBean(MenuService.class);

        //获取用户
        UserDo user = (UserDo) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        Set<String> menuPerms = menuService.menuPermsByUserId(user.getId());

        //获取用户角色
//        Set<String> roleSet = new HashSet<String>();
//        roleSet.addAll(menuPerms);
//        info.setRoles(roleSet);

        //获取用户权限

        info.setStringPermissions(menuPerms);
        return info;
    }
    /**
     * 验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserService userService = SpringContextHolder.getBean(UserService.class);
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        String password = String.valueOf(token.getPassword());
        QueryWrapper<UserDo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);
        UserDo userDo = userService.getOne(wrapper);
        if(userDo == null){
            log.error("当前用户不存在:{}",userName);
            throw new UnknownAccountException("账号或密码不正确");
        }
        if(!StringUtils.equals(password,userDo.getPassWord())){
            log.error("{}","账号或密码不正确");
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        // 账号锁定
        if (userDo.getDelFlag().getValue().equals(DelFlag.DELETE.getValue())) {
            log.error(":{}" , "账号已被锁定,请联系管理员");
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        //更新用户登录次数 及 最后一次登陆时间
        userDo.setLoginCount(userDo.getLoginCount()+1);
        userDo.setLastLoginTime(new Date());
        //更新操作
        userService.updateById(userDo);
        return new SimpleAuthenticationInfo(userDo, password, getName());
    }
}
