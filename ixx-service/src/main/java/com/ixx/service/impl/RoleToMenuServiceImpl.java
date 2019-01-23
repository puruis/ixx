package com.ixx.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ixx.dao.RoleDao;
import com.ixx.dao.RoleToMenuDao;
import com.ixx.entity.sys.MenuDo;
import com.ixx.entity.sys.RoleDo;
import com.ixx.entity.sys.RoleToMenuDo;
import com.ixx.service.RoleToMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@Slf4j
public class RoleToMenuServiceImpl extends ServiceImpl<RoleToMenuDao, RoleToMenuDo> implements RoleToMenuService{

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleToMenuDao roleToMenuDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRoleAndRoleToMenu(RoleDo roleDo, JSONArray array) {
        if(StringUtils.isNotBlank(roleDo.getId())){
            roleDao.updateById(roleDo);
            QueryWrapper<RoleToMenuDo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id",roleDo.getId());
            int delete = roleToMenuDao.delete(queryWrapper);
            log.debug("清除角色的:{},菜单数量:{}",roleDo.getRoleName(),delete);
        }else{
            roleDao.insert(roleDo);
        }
        List<RoleToMenuDo> roleToMenuDos = new ArrayList<>();
        for(int i=0;i<array.size();i++){
            JSONObject jsonObject = array.getJSONObject(i);
            String key = jsonObject.getString("id");
            RoleToMenuDo roleToMenuDo = new RoleToMenuDo();
            roleToMenuDo.setMenuId(key);
            roleToMenuDo.setRoleId(roleDo.getId());
            roleToMenuDos.add(roleToMenuDo);
        }
        saveOrUpdateBatch(roleToMenuDos);
    }

    @Override
    public List<MenuDo> queryMenuListByRoleId(String roleId) {
        return roleToMenuDao.queryMenuListByRoleId(roleId);
    }
}
