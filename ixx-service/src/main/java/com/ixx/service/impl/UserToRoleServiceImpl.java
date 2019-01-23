package com.ixx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ixx.dao.UserToRoleDao;
import com.ixx.entity.sys.RoleDo;
import com.ixx.entity.sys.UserToRoleDo;
import com.ixx.service.UserToRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:37
 */
@Service
public class UserToRoleServiceImpl extends ServiceImpl<UserToRoleDao, UserToRoleDo> implements UserToRoleService {

    @Autowired
    private UserToRoleDao userToRoleDao;

    @Override
    public List<RoleDo> queryRoleListByUserId(String userId) {
        return userToRoleDao.queryRoleListByUserId(userId);
    }

    @Override
    public List<String> queryRoleIdByUserId(String userId) {
        return userToRoleDao.queryRoleIdByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTransfer(String userId, List<String> roleIds) {
        //先清除 原来的 用户 和 角色关系
        QueryWrapper<UserToRoleDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        int delete = userToRoleDao.delete(queryWrapper);
        List<UserToRoleDo> userToRoleDos = new ArrayList<>();
        roleIds.forEach(roleId->{
            UserToRoleDo userToRoleDo = new UserToRoleDo();
            userToRoleDo.setUserId(userId);
            userToRoleDo.setRoleId(roleId);
            userToRoleDos.add(userToRoleDo);
        });
        // 防止出错 entityList must not be empty
        if(userToRoleDos.size()>0){
            saveBatch(userToRoleDos);
        }
    }
}
