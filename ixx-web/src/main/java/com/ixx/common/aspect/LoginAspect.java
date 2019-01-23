package com.ixx.common.aspect;

import com.ixx.util.HttpContextUtils;
import com.ixx.util.RequestInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:登录切面
 * User: purui_zhang
 * Date: 2018-12-21
 * Time: 12:31
 */
@Slf4j
@Aspect
@Component
public class LoginAspect {

    @Pointcut("@annotation(com.ixx.common.aspect.Login)")
    public void loginCutPoint(){

    }
    @Around("loginCutPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String ipAddress = RequestInfoUtil.getIpAddr(request);
        // 执行方法
        Object result = point.proceed();
        long endTime = System.currentTimeMillis();
        log.info("登录耗时:{} S,登录IP地址:{}",(endTime-beginTime)/1000,ipAddress);
        return result;
    }

}
