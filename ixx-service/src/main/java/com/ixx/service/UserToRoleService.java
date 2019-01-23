package com.ixx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ixx.entity.sys.RoleDo;
import com.ixx.entity.sys.UserToRoleDo;

import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-12
 * Time: 14:50
 */
public interface UserToRoleService extends IService<UserToRoleDo> {
    /**
     *  通过userId 查询 角色集合
     * @param userId
     * @return
     */
    List<RoleDo> queryRoleListByUserId(String userId);
    /**
     *  通过userId 查询 角色 ID 集合
     * @param userId
     * @return
     */
    List<String> queryRoleIdByUserId(String userId);

    /**
     *  保存 用户和角色关系
     * @param userId
     * @param roleIds
     */
    void saveTransfer(String userId, List<String> roleIds);
}
