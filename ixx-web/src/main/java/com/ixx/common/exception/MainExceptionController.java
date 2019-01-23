package com.ixx.common.exception;

import com.ixx.common.result.ResultJson;
import com.ixx.common.utils.ResponseUtils;
import com.ixx.util.RequestInfoUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-27
 * Time: 17:01
 */
@Controller
public class MainExceptionController implements ErrorController{

    private static final String ERROR_PATH = "/error";

    private static final Integer ERROR_401 = 401;
    private static final Integer ERROR_404 = 404;
    private static final Integer ERROR_403 = 403;
    private static final Integer ERROR_500 = 500;

    @RequestMapping(value = ERROR_PATH)
    public void handleError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode.equals(ERROR_401)) {
            if(RequestInfoUtil.isAjaxRequest(request)){
                ResultJson<String> json = new ResultJson<String>("",ERROR_401,"未授权");
                ResponseUtils.writeJson(response,json);
            }else{
                response.sendRedirect("/error/401");
            }
        }
        else if (statusCode.equals(ERROR_404)) {
            if(RequestInfoUtil.isAjaxRequest(request)){
                ResultJson<String> json = new ResultJson<String>("",ERROR_404,"找不到");
                ResponseUtils.writeJson(response,json);
            }else{
                response.sendRedirect("/error/404");
            }
        }
        else if (statusCode.equals(ERROR_403)) {
            if(RequestInfoUtil.isAjaxRequest(request)){
                ResultJson<String> json = new ResultJson<String>("",ERROR_403,"权限拒绝");
                ResponseUtils.writeJson(response,json);
            }else{
                response.sendRedirect("/error/403");
            }
        } else{
            if(RequestInfoUtil.isAjaxRequest(request)){
                ResultJson<String> json = new ResultJson<String>("",ERROR_500,"出错");
                ResponseUtils.writeJson(response,json);
            }else{
                response.sendRedirect("/error/500");
            }
        }
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }


}
