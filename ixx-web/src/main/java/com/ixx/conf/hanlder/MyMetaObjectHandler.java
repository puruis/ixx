package com.ixx.conf.hanlder;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ixx.common.enumeration.defaults.DelFlag;
import com.ixx.entity.sys.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Description: 公用字段自动填充
 * User: purui_zhang
 * Date: 2018-12-07
 * Time: 17:37
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        UserDo user = null;
        try{
            user = (UserDo) SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e){

        }
        log.info("start insert date:{}",new Date());
        String[] getterNames = metaObject.getGetterNames();
        List<String> list = Arrays.asList(getterNames);
        UserDo finalUser = user;
        list.forEach(attr->{
            if(StringUtils.equals(attr,"createTime" )){
                metaObject.setValue("createTime",new Date());
            }
            if(StringUtils.equals(attr,"loginTime" )){
                metaObject.setValue("loginTime",new Date());
            }
            if(StringUtils.equals(attr,"createUser" )){
                metaObject.setValue("createUser", finalUser!=null?finalUser.getUserName():"-");
            }
            if(StringUtils.equals(attr,"delFlag" )){
                metaObject.setValue("delFlag", DelFlag.NORMAL);
            }
        });
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        UserDo user = null;
        try{
            user = (UserDo) SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e){

        }
        log.info("start update date:{}",new Date());
        String[] getterNames = metaObject.getGetterNames();
        List<String> list = Arrays.asList(getterNames);
        UserDo finalUser = user;
        list.forEach(attr->{
            if(StringUtils.equals(attr,"updateTime" )){
                metaObject.setValue("updateTime",new Date());
            }
            if(StringUtils.equals(attr,"updateUser" )){
                metaObject.setValue("updateUser", finalUser !=null? finalUser.getUserName():"-");
            }
        });
    }
}
