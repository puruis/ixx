package com.ixx.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ixx.common.BaseController;
import com.ixx.common.enumeration.defaults.DelFlag;
import com.ixx.common.result.ResultJson;
import com.ixx.entity.sys.MenuDo;
import com.ixx.entity.sys.RoleDo;
import com.ixx.service.MenuService;
import com.ixx.service.RoleService;
import com.ixx.service.RoleToMenuService;
import com.ixx.service.UserToRoleService;
import com.ixx.vo.ZTree;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-12-04
 * Time: 22:04
 */
@Controller
@RequestMapping(value = "role")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleToMenuService roleToMenuService;
    @Autowired
    private UserToRoleService userToRoleService;
    @Value("${adminUserId}")
    private String adminUserId;
    /**
     *  角色列表主页跳转
     * @return
     */
    @RequiresPermissions("role:index")
    @GetMapping(value = "index")
    public String index(){
        return "role/list";
    }

    /**
     *  角色列表 数据
     * @return
     */
    @RequiresPermissions("role:index")
    @ResponseBody
    @GetMapping(value = "list")
    public Object list(String roleName,String roleRemk,String status){
        Page<RoleDo> page = getPage(RoleDo.class);
        QueryWrapper<RoleDo> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(roleName)){
            queryWrapper.like("role_name",roleName);
        }
        if(StringUtils.isNotBlank(roleRemk)){
            queryWrapper.like("role_remk",roleRemk);
        }
        if(StringUtils.isNotBlank(status)){
            queryWrapper.like("del_flag",status);
        }
        IPage<RoleDo> iPage = roleService.page(page, queryWrapper);
        return getResultData(iPage);
    }



    /**
     *  角色新增页面跳转
     * @return
     */
    @RequiresPermissions("role:save")
    @GetMapping(value = "addPage")
    public String addPage(){
        return "role/add";
    }


    /**
     *  批量删除 (逻辑删除)
     * @param ids
     * @param status
     * @return
     */
    @RequiresPermissions("role:batchDelete")
    @ResponseBody
    @PostMapping(value = "batchDelete")
    public Object batchDelete(@RequestParam("ids[]") String[] ids, String status){
        List<String> dictIds = Arrays.asList(ids);
        List<RoleDo> roleDos = (List<RoleDo>) roleService.listByIds(dictIds);
        roleDos.forEach(roleDo -> {
            if(StringUtils.equals("DELETE",status)){
                roleDo.setDelFlag(DelFlag.DELETE);
            }else{
                roleDo.setDelFlag(DelFlag.NORMAL);
            }
        });
        roleService.saveOrUpdateBatch(roleDos);
        return ResultJson.success();
    }

    /**
     *  详情页面跳转
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("role:detail")
    @GetMapping(value = "detail")
    public Object detail(String id, Model model){
        RoleDo roleDo = roleService.getById(id);
        model.addAttribute("roleDo",roleDo);
        return "role/detail";
    }

    /**
     *  编辑页面跳转
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("role:edit")
    @GetMapping(value = "edit")
    public Object edit(String id, Model model){
        RoleDo roleDo = roleService.getById(id);
        model.addAttribute("roleDo",roleDo);
        return "role/edit";
    }

    /**
     * 查询 角色集合
     * @return
     */
    @RequiresPermissions("role:menuList")
    @ResponseBody
    @PostMapping(value = "menuList")
    public Object queryMenuList(){
        List<String> menuIds;
        if(StringUtils.equals(adminUserId,getUserId() )){
            // sa 账号查询 所有
            menuIds = roleService.menuListId();
        }else{
            menuIds = roleService.menuListIdByUserId(getUserId());
        }
        List<ZTree> menuDos = menuService.queryMenuList(menuIds);
        return ResultJson.success(menuDos);
    }

    /**
     *  保存 角色 及 menu和角色的关系
     * @param roleDo
     * @param menuId
     * @return
     */
    @RequiresPermissions("role:save")
    @ResponseBody
    @PostMapping(value = "save")
    public Object saveRole(RoleDo roleDo,String menuId){
        JSONArray object = JSONArray.parseArray(menuId);
        roleToMenuService.saveRoleAndRoleToMenu(roleDo,object);
        return ResultJson.success();
    }


    /**
     *  通过角色id查询 菜单集合
     * @param roleId
     * @return
     */
    @RequiresPermissions("role:queryMenuListByRoleId")
    @ResponseBody
    @GetMapping(value = "queryMenuListByRoleId")
    public Object queryMenuIdListByRoleId(String roleId){
        List<String> menuIds;
        if(StringUtils.equals(adminUserId,getUserId() )){
            // sa 账号查询 所有
            menuIds = roleService.menuListId();
        }else{
            menuIds = roleService.menuListIdByUserId(getUserId());
        }
        List<ZTree> allMenuDos = menuService.queryMenuList(menuIds);
        List<MenuDo> list = roleToMenuService.queryMenuListByRoleId(roleId);
        allMenuDos.forEach(zTree -> {
            list.forEach(menuDo -> {
                if (StringUtils.equals(zTree.getId(),menuDo.getId())){
                    zTree.setChecked(true);
                }
            });
        });
        return ResultJson.success(allMenuDos);
    }

    /**
     *  通过 userId 查询 角色集合
     * @param userId
     * @return
     */
    @RequiresPermissions("role:queryRoleListByUserId")
    @ResponseBody
    @PostMapping(value = "queryRoleListByUserId")
    public Object queryRoleListByUserId(String userId){
        List<RoleDo> list = userToRoleService.queryRoleListByUserId(userId);
        return ResultJson.success(list);
    }


    /**
     *  通过 userId 查询 所有角色集合 排除 拥有的
     * @param userId
     * @return
     */
    @RequiresPermissions("role:queryRoleListByUserIdExcludeOwned")
    @ResponseBody
    @PostMapping(value = "queryRoleListByUserIdExcludeOwned")
    public Object queryRoleListByUserIdExcludeOwned(String userId){
        List<String> ids = userToRoleService.queryRoleIdByUserId(userId);
        QueryWrapper<RoleDo> wrapper = new QueryWrapper<>();
        wrapper.notIn("id",ids);
        List<RoleDo> roleDoList = roleService.list(wrapper);
        return ResultJson.success(roleDoList);
    }

}
