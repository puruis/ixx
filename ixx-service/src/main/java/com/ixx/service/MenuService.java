package com.ixx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ixx.entity.sys.MenuDo;
import com.ixx.vo.MenuTree;
import com.ixx.vo.MenuTree2;
import com.ixx.vo.ZTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:37
 */
public interface MenuService extends IService<MenuDo> {

    /**
     *  根据 userId  查询 权限
     * @param id
     * @return
     */
    Set<String> menuPermsByUserId(String id);

    /**
     *  根据UserId 查询 菜单 tree
     * @param id
     * @return
     */
    LinkedList<MenuTree> queryMenuTreeListByUserId(String id);
    /**
     *  根据UserId 查询 菜单 list
     * @param id
     * @return
     */
    List<MenuDo> queryMenuListByUserId(String id);

    List<MenuTree2> queryMenuTreeListByUserId2(String userId);

    /**
     *  查询所有查单
     * @return
     */
    List<MenuDo> queryMenuAll(String menuName,String perms,String url);

    /**
     *  通过 父id 查询 子菜单
     * @param parentId
     * @return
     */
    List<MenuDo> queryMenuByParentId(String parentId);

    /**
     *  查询所有菜单 通过id 集合
     * @return
     */
    List<ZTree>  queryMenuList(List<String> menuIds);

}
