package com.ixx.common.exception;

import com.ixx.common.enums.EnumErrorCode;
import com.ixx.common.result.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-27
 * Time: 17:05
 */
@Slf4j
@RestControllerAdvice
public class MainExceptionHandler {
    /**
     * 自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResultJson<String> handleBDException(BusinessException e) {
        try {
            int code = Integer.parseInt(e.getMessage());
            return ResultJson.build(code, null,EnumErrorCode.getMsgByCode(code));
        } catch (NumberFormatException e1) {
            log.info("错误码使用错误，异常内容请抛出EnumErrorCode类的枚举值");
            return ResultJson.build(EnumErrorCode.unknowFail.getCode(),null, EnumErrorCode.unknowFail.getMsg());
        }
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResultJson<String> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return ResultJson.build(EnumErrorCode.duplicateKeyExist.getCode(),null, EnumErrorCode.duplicateKeyExist.getMsg());
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultJson<String> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return ResultJson.build(EnumErrorCode.requestNoSupport.getCode(),null, EnumErrorCode.requestNoSupport.getMsg());
    }

    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public ResultJson<String> noHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        return ResultJson.build(EnumErrorCode.pageNotFound.getCode(),null, EnumErrorCode.pageNotFound.getMsg());
    }
    @ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "程序员哥哥正在解决")
    @ExceptionHandler(AuthorizationException.class)
    public ResultJson<String> handleAuthorizationException(AuthorizationException e) {
        log.error(e.getMessage(), e);
        return ResultJson.build(EnumErrorCode.notAuthorization.getCode(),null, EnumErrorCode.notAuthorization.getMsg());
    }
    @ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "程序员哥哥正在解决")
    @ExceptionHandler(UnauthorizedException.class)
    public ResultJson<String> handleUnauthorizedException(AuthorizationException e) {
        log.error(e.getMessage(), e);
        return ResultJson.build(EnumErrorCode.notAuthorization.getCode(),null, EnumErrorCode.notAuthorization.getMsg());
    }
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,reason = "程序员哥哥正在解决")
    @ExceptionHandler(Exception.class)
    public ResultJson<String> handleException(Exception e){
        log.error(e.getMessage(), e);
        return ResultJson.build(EnumErrorCode.unknowFail.getCode(),null, EnumErrorCode.unknowFail.getMsg());
    }
}
