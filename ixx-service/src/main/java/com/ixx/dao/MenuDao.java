package com.ixx.dao;

import com.ixx.common.BaseDao;
import com.ixx.entity.sys.MenuDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:35
 */
public interface MenuDao extends BaseDao<MenuDo> {
    Set<String> menuPermsByUserId(String id);

    List<MenuDo> queryMenuListByUserId(String id);
    List<MenuDo> queryMenuAll(@Param("menuName")String menuName,@Param("perms") String perms,@Param("url") String url);
}
