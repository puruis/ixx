package com.ixx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ixx.dao.RoleDao;
import com.ixx.entity.sys.RoleDo;
import com.ixx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:37
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleDo> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<String> menuListIdByUserId(String userId) {
        return roleDao.menuListIdByUserId(userId);
    }

    @Override
    public List<String> menuListId() {
        return roleDao.menuListId();
    }
}
