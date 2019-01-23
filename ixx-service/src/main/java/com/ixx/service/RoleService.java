package com.ixx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ixx.entity.sys.RoleDo;

import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:37
 */
public interface RoleService extends IService<RoleDo> {
    /**
     *  根据 用户id 查询 菜单集合
     * @param userId
     * @return
     */
    List<String> menuListIdByUserId(String userId);

    /**
     *  sa 查询 all 查单集合
     * @return
     */
    List<String> menuListId();
}
