package com.ixx.dao;

import com.ixx.common.BaseDao;
import com.ixx.entity.sys.MenuDo;
import com.ixx.entity.sys.RoleToMenuDo;

import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:36
 */
public interface RoleToMenuDao extends BaseDao<RoleToMenuDo> {
    List<MenuDo> queryMenuListByRoleId(String roleId);

}
