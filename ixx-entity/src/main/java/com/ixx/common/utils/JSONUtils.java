package com.ixx.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.ixx.common.result.ResultJson;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-12-13
 * Time: 22:19
 */
public class JSONUtils {

    /**
     *  对象转 json 字符串
     * @param resultJson
     * @return
     */
    public static String Obj2JsonStr(ResultJson resultJson){
        return JSONObject.toJSONString(resultJson);
    }
}
