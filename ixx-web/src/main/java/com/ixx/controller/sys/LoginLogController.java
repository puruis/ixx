package com.ixx.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ixx.common.BaseController;
import com.ixx.entity.sys.LoginNoticeDo;
import com.ixx.service.LoginNoticeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-12-26
 * Time: 21:25
 */
@Controller
@RequestMapping(value = "loginLog")
public class LoginLogController extends BaseController{

    @Autowired
    private LoginNoticeService loginNoticeService;

    @GetMapping(value = "index")
    public String index(){
        return "loginlog/list";
    }

    /**
     *  登录日志数据列表
     * @return
     */
    @RequiresPermissions("loginLog:list")
    @ResponseBody
    @GetMapping(value = "list")
    public Object list(String userName,String address,String operatingSys){
        Page<LoginNoticeDo> page = getPage(LoginNoticeDo.class);
        QueryWrapper<LoginNoticeDo> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(userName)){
            queryWrapper.like("user_name",userName);
        }
        if(StringUtils.isNotBlank(address)){
            queryWrapper.like("address",address);
        }
        if(StringUtils.isNotBlank(operatingSys)){
            queryWrapper.like("operating_sys",operatingSys);
        }
        queryWrapper.orderByDesc("login_time");
        IPage<LoginNoticeDo> iPage = loginNoticeService.page(page, queryWrapper);
        Map resultData = getResultData(iPage);
        return resultData;
    }
}
