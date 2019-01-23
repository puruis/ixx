package com.ixx.common.utils;


import com.ixx.common.result.ResultJson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-12-13
 * Time: 22:39
 */
public class ResponseUtils {

    public static void writeJson(HttpServletResponse response,ResultJson json) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONUtils.Obj2JsonStr(json));
    }
}
