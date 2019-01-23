package com.ixx.dao;

import com.ixx.common.BaseDao;
import com.ixx.entity.sys.RoleDo;

import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:35
 */
public interface RoleDao  extends BaseDao<RoleDo> {
    List<String> menuListIdByUserId(String userId);

    List<String> menuListId();

}
