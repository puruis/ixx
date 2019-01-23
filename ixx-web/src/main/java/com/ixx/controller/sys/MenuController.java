package com.ixx.controller.sys;

import com.ixx.common.BaseController;
import com.ixx.common.result.ResultJson;
import com.ixx.entity.sys.MenuDo;
import com.ixx.service.MenuService;
import com.ixx.vo.MenuTree;
import com.ixx.vo.MenuTree2;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-21
 * Time: 19:34
 */
@Controller
@RequestMapping("menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @GetMapping("")
    @RequiresPermissions("menu:index")
    public String menuIndex(){
        return "menu/list";
    }

    /**
     *  menuTree 后台做 父子节点处理
     * @return
     */
    @RequiresPermissions("menu:menuListTree")
    @ResponseBody
    @GetMapping(value = "menuListTree")
    public ResultJson<List<MenuTree>> menuListTree(){
        List<MenuTree> menuTrees = menuService.queryMenuTreeListByUserId(getUserId());
        ResultJson resultJson = new ResultJson(menuTrees,ResultJson.SUCCESS,ResultJson.SUCCESS_MESSAGE);
        return resultJson;
    }

    /**
     *  menuTree 后台做 父子节点处理 tree菜单选择
     * @return
     */
    @RequiresPermissions("menu:menuListTree")
    @ResponseBody
    @GetMapping(value = "menuListTree2")
    public ResultJson<List<MenuTree2>> menuListTree2(){
        List<MenuTree2> menuTrees = menuService.queryMenuTreeListByUserId2(getUserId());
        ResultJson resultJson = new ResultJson(menuTrees,ResultJson.SUCCESS,ResultJson.SUCCESS_MESSAGE);
        return resultJson;
    }

    /**
     *  menuList 后台不做父子节点处理
     * @return
     */
    @RequiresPermissions("menu:menuListTree")
    @ResponseBody
    @GetMapping(value = "menuList")
    public ResultJson<List<MenuDo>> menuList(String menuName,String perms,String url){
        List<MenuDo> menuTrees = menuService.queryMenuAll(menuName,perms,url);
        ResultJson resultJson = new ResultJson(menuTrees,ResultJson.SUCCESS,ResultJson.SUCCESS_MESSAGE);
        return resultJson;
    }


    /**
     * 跳转 菜单新增页面
     * @return
     */
    @RequiresPermissions("menu:saveMenu")
    @GetMapping("menuAdd")
    public String menuAdd(){
        return "menu/add";
    }

    /**
     * 跳转菜单树 页面
     * @return
     */
    @RequiresPermissions("menu:menuTree")
    @GetMapping("menuTree")
    public String menuTree(){
        return "menu/menuTree";
    }

    /**
     *  保存菜单
     * @return
     */
    @ResponseBody
    @RequiresPermissions("menu:saveMenu")
    @PostMapping(value = "saveMenu")
    public ResultJson<String> saveMenu(MenuDo menuDo){
        boolean save = menuService.saveOrUpdate(menuDo);
        if(save){
            return ResultJson.success();
        }else{
            return ResultJson.fail();
        }
    }

    /**
     * 跳转icon选择页面
     * @return
     */
    @GetMapping("iconPage")
    @RequiresPermissions("menu:iconPage")
    public String iconPage(){
        return "menu/icon";
    }

    /**
     *  删除
     * @return
     */
    @RequiresPermissions("menu:deleteMenu")
    @ResponseBody
    @PostMapping(value = "deleteMenu")
    public ResultJson<String> deleteMenu(String id){
        boolean save = menuService.removeById(id);
        if(save){
            return ResultJson.success();
        }else{
            return ResultJson.fail();
        }
    }

    /**
     *  菜单编辑
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("menu:edit")
    @GetMapping("edit")
    public String edit(String id, Model model){
        MenuDo menuDo = menuService.getById(id);
        MenuDo parentMenuDo = menuService.getById(menuDo.getPid());
        model.addAttribute("menuDo",menuDo);
        model.addAttribute("parentMenuDo",parentMenuDo);
        return "menu/edit";
    }

}
