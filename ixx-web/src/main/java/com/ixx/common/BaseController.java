package com.ixx.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ixx.entity.sys.UserDo;
import com.ixx.util.HttpContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-14
 * Time: 23:44
 */
@Controller
public class BaseController {

    public UserDo getUser(){
        UserDo user = (UserDo)SecurityUtils.getSubject().getPrincipal();
        return user;
    }

    public <E> Page<E> getPage(Class<E> e) {
        int pageNumber = getParaToInt("page" , 1);
        int pageSize = getParaToInt("limit" , 10);
        return new Page<>(pageNumber, pageSize);
    }

    private int getParaToInt(String key, int defalut) {
        String pageNumber = HttpContextUtils.getHttpServletRequest().getParameter(key);
        if (StringUtils.isBlank(pageNumber)) {
            return defalut;
        }
        return Integer.parseInt(pageNumber);
    }

    public String getUserId(){
        return getUser().getId();
    }

    public HttpSession getSession(HttpServletRequest request){
        return request.getSession();
    }

    public Map getResultData(IPage page){
        Map dictDoHashMap = new HashMap<String,Object>(5);
        dictDoHashMap.put("code","0");
        dictDoHashMap.put("msg","获取数据成功");
        dictDoHashMap.put("count",page.getTotal());
        dictDoHashMap.put("data",page.getRecords());
        return dictDoHashMap;
    }
}
