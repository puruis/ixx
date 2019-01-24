package com.ixx.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ixx.common.BaseController;
import com.ixx.service.SessionService;
import com.ixx.vo.UserOnline;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2019-01-24
 * Time: 16:02
 */
@Controller
@RequestMapping(value = "online")
public class OnlineController extends BaseController{

    @Autowired
    private SessionService sessionService;

    /**
     *  用户在线列表页面跳转
     * @return
     */
    @RequiresPermissions("online:index")
    @GetMapping(value = "")
    public String index(){
        return "online/list";
    }

    /**
     *  用户在线列表数据
     * @return
     */
    @RequiresPermissions("online:index")
    @ResponseBody
    @GetMapping(value = "list")
    public Object list(){
        Page<UserOnline> page = getPage(UserOnline.class);
        List<UserOnline> list = sessionService.list();
        page.setRecords(list);
        page.setTotal(list.size());
        return getResultData(page);
    }
}
