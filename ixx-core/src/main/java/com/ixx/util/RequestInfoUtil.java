package com.ixx.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 请求信息获取
 * User: zpr
 * Date: 2018-03-28
 * Time: 10:45
 */
@SuppressWarnings("ALL")
public class RequestInfoUtil {

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("X-Real-IP");
        //noinspection AlibabaUndefineMagicConstant
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip))
        {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        //noinspection AlibabaUndefineMagicConstant
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip))
        {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1)
            {
                return ip.substring(0, index);
            }
            else
            {
                return ip;
            }
        }
        else
        {
            return request.getRemoteAddr();
        }
    }

    /**
     *  判断是否是 ajax 请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}
