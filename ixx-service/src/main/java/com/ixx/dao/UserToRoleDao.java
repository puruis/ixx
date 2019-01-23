package com.ixx.dao;

import com.ixx.common.BaseDao;
import com.ixx.entity.sys.RoleDo;
import com.ixx.entity.sys.UserToRoleDo;

import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:36
 */
public interface UserToRoleDao extends BaseDao<UserToRoleDo>{
    List<RoleDo> queryRoleListByUserId(String userId);

    List<String> queryRoleIdByUserId(String userId);
}
