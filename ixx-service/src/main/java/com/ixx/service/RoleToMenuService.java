package com.ixx.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ixx.entity.sys.MenuDo;
import com.ixx.entity.sys.RoleDo;
import com.ixx.entity.sys.RoleToMenuDo;

import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-12
 * Time: 14:50
 */
public interface RoleToMenuService extends IService<RoleToMenuDo> {
    /**
     *  保存 角色 和 角色与菜单的关系
     * @param roleDo
     * @param object
     */
    void saveRoleAndRoleToMenu(RoleDo roleDo, JSONArray object);

    /**
     *  通过角色id 查询到 所有菜单的id
     * @param roleId
     * @return
     */
    List<MenuDo> queryMenuListByRoleId(String roleId);
}
