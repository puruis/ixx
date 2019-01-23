package com.ixx.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ixx.common.BaseController;
import com.ixx.common.aspect.Login;
import com.ixx.common.enumeration.defaults.DelFlag;
import com.ixx.common.result.ResultJson;
import com.ixx.entity.sys.LoginNoticeDo;
import com.ixx.entity.sys.UserDo;
import com.ixx.service.LoginNoticeService;
import com.ixx.service.UserService;
import com.ixx.service.UserToRoleService;
import com.ixx.util.MD5Utils;
import com.ixx.util.RequestInfoUtil;
import com.ixx.util.UserAgentUtil;
import cz.mallat.uasparser.UserAgentInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-12
 * Time: 14:40
 */
@Slf4j
@Controller
public class UserController extends BaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private UserToRoleService userToRoleService;
    @Autowired
    private LoginNoticeService loginNoticeService;

    @GetMapping(value = "login")
    public String login(){
        return "login";
    }

    @Login
    @ResponseBody
    @PostMapping(value = "ajaxLogin")
    public ResultJson<String> ajaxLogin(UserDo userDo,HttpServletRequest request){
        String password = MD5Utils.encrypt(userDo.getUserName(), userDo.getPassWord());
        UsernamePasswordToken token = new UsernamePasswordToken(userDo.getUserName(), password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            try{
                //保存用户登录信息
                UserDo one = (UserDo) subject.getPrincipals().getPrimaryPrincipal();
                String ipAddress = RequestInfoUtil.getIpAddr(request);
                LoginNoticeDo loginNoticeDo = new LoginNoticeDo();
                String device = request.getHeader("User-Agent");
                UserAgentInfo agentInfo = UserAgentUtil.uasParser.parse(device);
                String osName = agentInfo.getOsName();
                String uaName = agentInfo.getUaName();
                loginNoticeDo.setOperatingSys(osName);
                loginNoticeDo.setBrowser(uaName);
                loginNoticeDo.setIp(ipAddress);
                loginNoticeDo.setLoginTime(new Date());
                loginNoticeDo.setUserName(one.getUserName());
                loginNoticeService.save(loginNoticeDo);
            }catch (Exception e){
                log.error("登录信息保存失败:{}",e );
            }
            return ResultJson.success();
        } catch (AuthenticationException e) {
            return ResultJson.fail("登录失败");
        }
    }
    @GetMapping(value = "index")
    public String index(Model model){
        UserDo user = getUser();
        model.addAttribute("user",user);
        return "index";
    }
    @GetMapping(value = "loginOut")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:login";
    }

    /**
     *  用户列表页面
     * @return
     */
    @RequiresPermissions("user:list")
    @GetMapping(value = "list")
    public String list(){
        return "user/list";
    }

    /**
     *  用户列表数据
     * @return
     */
    @RequiresPermissions("user:list")
    @ResponseBody
    @GetMapping(value = "listData")
    public Object listData(String userName,String nickName,String email){
        Page<UserDo> page = getPage(UserDo.class);
        QueryWrapper<UserDo> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(userName)){
            queryWrapper.like("user_name", userName);
        }
        if(StringUtils.isNotBlank(nickName)){
            queryWrapper.like("nick_name",nickName);
        }
        if(StringUtils.isNotBlank(email)){
            queryWrapper.like("email", email);
        }
        IPage<UserDo> userDoIPage = userService.page(page, queryWrapper);
        return getResultData(userDoIPage);
    }


    /**
     *  批量禁用操作
     * @param ids
     * @param status
     * @return
     */
    @RequiresPermissions("user:batchDelete")
    @ResponseBody
    @PostMapping(value = "batchDelete")
    public Object batchDelete(@RequestParam("ids[]") String[] ids, String status){
        List<String> dictIds = Arrays.asList(ids);
        List<UserDo> userDos = (List<UserDo>) userService.listByIds(dictIds);
        userDos.forEach(userDo -> {
            if(StringUtils.equals("DELETE",status)){
                userDo.setDelFlag(DelFlag.DELETE);
            }else{
                userDo.setDelFlag(DelFlag.NORMAL);
            }
        });
        userService.saveOrUpdateBatch(userDos);
        return ResultJson.success();
    }


    /**
     *  详情页面跳转
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("user:detail")
    @GetMapping(value = "/user/detail")
    public Object detail(String id, Model model){
        UserDo userDo = userService.getById(id);
        model.addAttribute("userDo",userDo);
        return "user/detail";
    }

    /**
     *  编辑页面跳转
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("user:edit")
    @GetMapping(value = "/user/edit")
    public Object edit(String id, Model model){
        UserDo userDo = userService.getById(id);
        model.addAttribute("userDo",userDo);
        return "user/edit";
    }


    /**
     * 保存操作
     * @param userDo
     * @return
     */
    @RequiresPermissions("user:saveUser")
    @ResponseBody
    @PostMapping(value = "/user/saveUser")
    public Object saveDict(UserDo userDo){
        boolean save = userService.saveOrUpdate(userDo);
        return ResultJson.success();
    }

    /**
     *  用户角色穿梭
     * @param model
     * @return
     */
    @RequiresPermissions("user:saveTransfer")
    @GetMapping(value = "/user/transfer")
    public Object transfer(String userId,Model model){
        UserDo userDo = userService.getById(userId);
        model.addAttribute("userDo",userDo);
        return "user/transfer";
    }

    /**
     *  保存角色 用户关系
     * @param json
     * @param userId
     * @return
     */
    @RequiresPermissions("user:saveTransfer")
    @ResponseBody
    @PostMapping(value = "/user/saveTransfer")
    public Object saveTransfer(String json,String userId){
        JSONArray roleArr = JSONArray.parseArray(json);
        List<String> roleIds = new ArrayList<>();
        for(int i=0;i<roleArr.size();i++){
            JSONObject role = roleArr.getJSONObject(i);
            String id = role.getString("id");
            roleIds.add(id);
        }
        userToRoleService.saveTransfer(userId,roleIds);
        return ResultJson.success();
    }

    /**
     *  跳转 用户基础信息界面
     * @return
     */
    @RequiresPermissions("user:baseInfo")
    @GetMapping(value = "user/baseInfo")
    public String baseInfo(Model model){
        UserDo userDo = userService.getById(getUserId());
        model.addAttribute("user",userDo);
        Page<LoginNoticeDo> objectPage = new Page<>(0, 1);
        QueryWrapper<LoginNoticeDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",getUser().getUserName());
        queryWrapper.orderByDesc("login_time");
        IPage<LoginNoticeDo> page = loginNoticeService.page(objectPage, queryWrapper);
        model.addAttribute("address",page.getRecords().size()>0?page.getRecords().get(0):null);
        return "user/baseInfo";
    }
    /**
     *  跳转 用户修改密码界面
     * @return
     */
    @RequiresPermissions("user:updatePassWordPage")
    @GetMapping(value = "user/updatePassWordPage")
    public String updatePassWord(Model model){
        UserDo userDo = userService.getById(getUserId());
        model.addAttribute("user",userDo);
        return "user/updatePassWord";
    }

    /**
     * 修改密码
     * @param id
     * @param oldPassWord
     * @param newPassWord
     * @return
     */
    @RequiresPermissions("user:updatePassWord")
    @ResponseBody
    @PostMapping(value = "user/updatePassWord")
    public Object updatePassWord(String id,String oldPassWord,String newPassWord){
        UserDo userDo = userService.getById(id);
        if(userDo==null){
            return ResultJson.failMsg("当前用户不存在!");
        }
        String oldPassword = MD5Utils.encrypt(userDo.getUserName(), oldPassWord);
        String newPassword = MD5Utils.encrypt(userDo.getUserName(), newPassWord);
        if(!StringUtils.equals(oldPassword,userDo.getPassWord())){
            return ResultJson.failMsg("原始密码不正确!");
        }
        userDo.setPassWord(newPassword);
        userService.updateById(userDo);
        return ResultJson.successMsg("密码修改完成!");
    }


}
