package com.ixx.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ixx.common.BaseController;
import com.ixx.common.enumeration.defaults.DelFlag;
import com.ixx.common.result.ResultJson;
import com.ixx.entity.sys.DictDo;
import com.ixx.service.DictService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-12
 * Time: 14:40
 */
@Controller
@RequestMapping(value = "dict")
public class DictController extends BaseController{

    @Autowired
    DictService dictService;

    /**
     *  字典列表页面跳转
     * @return
     */
    @RequiresPermissions("dict:index")
    @GetMapping(value = "")
    public String index(){
        return "dict/list";
    }

    /**
     *  字典列表数据
     * @param name
     * @param value
     * @param type
     * @return
     */
    @RequiresPermissions("dict:index")
    @ResponseBody
    @GetMapping(value = "list")
    public Object list(String name,String value,String type){
        Page<DictDo> page = getPage(DictDo.class);
        QueryWrapper<DictDo> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.like("name",name);
        }
        if(StringUtils.isNotBlank(value)){
            queryWrapper.like("value",value);
        }
        if(StringUtils.isNotBlank(type)){
            queryWrapper.like("type",type);
        }
        queryWrapper.orderBy(true,false,"sort");
        IPage<DictDo> iPage = dictService.page(page, queryWrapper);
        return getResultData(iPage);
    }

    /**
     *  字典新增页面跳转
     * @return
     */
    @RequiresPermissions("dict:saveDict")
    @GetMapping(value = "addPage")
    public String addPage(){
        return "dict/add";
    }

    /**
     * 保存操作
     * @param dictDo
     * @return
     */
    @RequiresPermissions("dict:saveDict")
    @ResponseBody
    @PostMapping(value = "saveDict")
    public Object saveDict(DictDo dictDo){
        boolean save = dictService.saveOrUpdate(dictDo);
        return ResultJson.success();
    }


    /**
     *  批量禁用操作
     * @param ids
     * @param status
     * @return
     */
    @RequiresPermissions("dict:batchDelete")
    @ResponseBody
    @PostMapping(value = "batchDelete")
    public Object batchDelete(@RequestParam("ids[]") String[] ids,String status){
        List<String> dictIds = Arrays.asList(ids);
        List<DictDo> dictDos = (List<DictDo>) dictService.listByIds(dictIds);
        dictDos.forEach(dictDo -> {
            if(StringUtils.equals("DELETE",status)){
                dictDo.setDelFlag(DelFlag.DELETE);
            }else{
                dictDo.setDelFlag(DelFlag.NORMAL);
            }
        });
        dictService.saveOrUpdateBatch(dictDos);
        return ResultJson.success();
    }

    /**
     *  详情页面跳转
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("dict:detail")
    @GetMapping(value = "detail")
    public Object detail(String id, Model model){
        DictDo dictDo = dictService.getById(id);
        model.addAttribute("dictDo",dictDo);
        return "dict/detail";
    }

    /**
     *  编辑页面跳转
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("dict:edit")
    @GetMapping(value = "edit")
    public Object edit(String id, Model model){
        DictDo dictDo = dictService.getById(id);
        model.addAttribute("dictDo",dictDo);
        return "dict/edit";
    }

    /**
     *  根据 字典类型 获取字典数据
     * @param menuType
     * @return
     */
    @ResponseBody
    @GetMapping(value = "getDictByDicyType")
    public Object getDictByDicyType(String menuType){
        if(StringUtils.isBlank(menuType)){
            return ResultJson.fail();
        }
        QueryWrapper<DictDo> wrapper = new QueryWrapper<>();
        wrapper.eq("type",menuType);
        List<DictDo> list = dictService.list(wrapper);
        return ResultJson.success(list);
    }

}
