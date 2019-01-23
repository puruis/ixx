package com.ixx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ixx.dao.MenuDao;
import com.ixx.entity.sys.MenuDo;
import com.ixx.service.MenuService;
import com.ixx.vo.MenuTree;
import com.ixx.vo.MenuTree2;
import com.ixx.vo.ZTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:37
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuDo> implements MenuService{

    @Autowired
    MenuDao menuDao;

    @Override
    public Set<String> menuPermsByUserId(String id) {
        return menuDao.menuPermsByUserId(id);
    }

    @Override
    public LinkedList<MenuTree> queryMenuTreeListByUserId(String id) {
        List<MenuDo> menuDos = menuDao.queryMenuListByUserId(id);
        LinkedList<MenuTree> menuTrees = new LinkedList<>();
        buildMenuTree(menuDos,menuTrees,"0");
        return menuTrees;
    }
    @Override
    public LinkedList<MenuTree2> queryMenuTreeListByUserId2(String id) {
        List<MenuDo> menuDos = menuDao.queryMenuListByUserId(id);
        LinkedList<MenuTree2> menuTrees = new LinkedList<>();
        buildMenuTree2(menuDos,menuTrees,"0");
        return menuTrees;
    }

    @Override
    public List<MenuDo> queryMenuAll(String menuName,String perms,String url) {
        List<MenuDo> menuDos = menuDao.queryMenuAll(menuName, perms, url);
        return menuDos;
    }

    @Override
    public List<MenuDo> queryMenuByParentId(String parentId) {
        QueryWrapper<MenuDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",parentId);
        List<MenuDo> menuDoList = menuDao.selectList(queryWrapper);
        return menuDoList;
    }

    @Override
    public List<ZTree> queryMenuList(List<String> menuIds) {
        List<ZTree> trees = new ArrayList<ZTree>();
        List<MenuDo> menuDOs = baseMapper.selectBatchIds(menuIds);
        for (MenuDo sysMenuDO : menuDOs) {
            ZTree<MenuDo> tree = new ZTree<MenuDo>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setpId(sysMenuDO.getPid());
            tree.setName(sysMenuDO.getMenuName());
            trees.add(tree);
        }
        return trees;
    }

    @Override
    public List<MenuDo> queryMenuListByUserId(String id) {
        List<MenuDo> menuDos = menuDao.queryMenuListByUserId(id);
        return menuDos;
    }

    /**
     *  menuList 转换成 menuTree
     * @param menuDos
     * @param menuTrees
     */
    public void buildMenuTree(List<MenuDo> menuDos,LinkedList<MenuTree> menuTrees,String pid){
        if(menuDos == null || menuDos.size() == 0){
            return;
        }
        menuDos.forEach(menuDo -> {
            MenuTree menuTree = new MenuTree();
            if(StringUtils.equals(menuDo.getPid(),pid)){
                menuTree.setId(menuDo.getId());
                menuTree.setName(menuDo.getMenuName());
                menuTree.setUrl(menuDo.getMenuUrl());
                menuTree.setOrder(menuDo.getOrders());
                menuTree.setPid("0");
                menuTree.setAuthority(menuDo.getPerms());
                menuTree.setType(menuDo.getType());
                menuTree.setIcon(menuDo.getIcon());
                LinkedList<MenuTree> menuTreesTemp = new LinkedList<>();
                buildMenuTree(menuDos,menuTreesTemp,menuDo.getId());
                menuTree.setList(menuTreesTemp);
                menuTrees.add(menuTree);
//                menuDos.forEach(menuDo1 -> {
//                    if(StringUtils.equals(menuDo1.getPid(),menuDo.getId())){
//                        MenuTree menuTree1 = new MenuTree();
//                        menuTree1.setId(menuDo1.getId());
//                        menuTree1.setName(menuDo1.getMenuName());
//                        menuTree1.setUrl(menuDo1.getMenuUrl());
//                        menuTree1.setOrder(menuDo1.getOrders());
//                        menuTree1.setPid("0");
//                        menuTree1.setAuthority(menuDo1.getPerms());
//                        menuTree1.setType(menuDo1.getType());
//                        menuTree1.setIcon(menuDo1.getIcon());
//                        LinkedList<MenuTree> menuTreesTemp1 = new LinkedList<>();
//                        buildMenuTree(menuDos,menuTreesTemp1,menuDo1.getId());
//                        menuTree.setList(menuTreesTemp);
//                        menuTrees.add(menuTree);
//                    }
//                });
            }
        });
    }

    /**
     *  menuList 转换成 menuTree
     * @param menuDos
     * @param menuTrees
     */
    public void buildMenuTree2(List<MenuDo> menuDos,LinkedList<MenuTree2> menuTrees,String pid){
        if(menuDos == null || menuDos.size() == 0){
            return;
        }
        menuDos.forEach(menuDo -> {
            if(menuDo.getType()!=2){
                MenuTree2 menuTree = new MenuTree2();
                if(StringUtils.equals(menuDo.getPid(),pid)){
                    menuTree.setName(menuDo.getMenuName());
                    menuTree.setId(menuDo.getId());
                    menuTree.setSpread(true);
                    LinkedList<MenuTree2> menuTreesTemp = new LinkedList<>();
                    buildMenuTree2(menuDos,menuTreesTemp,menuDo.getId());
                    menuTree.setChildren(menuTreesTemp);
                    menuTrees.add(menuTree);
                }
            }
        });
    }
}
