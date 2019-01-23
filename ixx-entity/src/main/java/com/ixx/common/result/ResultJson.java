package com.ixx.common.result;

/**
 * Description: 响应json
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 22:45
 */
public class ResultJson<T> {
    public static final Integer SUCCESS = 0;
    public static final Integer FAIL = 1;
    public static final String SUCCESS_MESSAGE = "成功";
    public static final String FAIL_MESSAGE = "失败";

    private T data;
    private Integer code;
    private String msg;

    public ResultJson(T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
    public  ResultJson(T data){
        this.code = ResultJson.SUCCESS;
        this.msg = "成功";
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static <T> ResultJson<T> success(T data){
        return new ResultJson<T>(data);
    }
    public static <T> ResultJson<T> success(){
        return new ResultJson<T>(null,ResultJson.SUCCESS,"成功");
    }
    public static <T> ResultJson<T> successMsg(String msg){
        return new ResultJson<T>(null,ResultJson.SUCCESS,msg);
    }

    public static <T> ResultJson<T> fail(T data){
        return new ResultJson<T>(data,ResultJson.FAIL,ResultJson.FAIL_MESSAGE);
    }
    public static <T> ResultJson<T> fail(){
        return new ResultJson<T>(null,ResultJson.FAIL,"失败");
    }
    public static <T> ResultJson<T> failMsg(String msg){
        return new ResultJson<T>(null,ResultJson.FAIL,msg);
    }

    public static <T> ResultJson build(Integer code,T data,String msg){
        return new ResultJson<T>(data,code,msg);
    }

}
