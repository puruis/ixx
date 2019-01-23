package com.ixx.util;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;

import java.io.IOException;
/**
 * Description: UserAgent 对象解析
 * User: purui_zhang
 * Date: 2018-12-27
 * Time: 20:25
 */
public class UserAgentUtil {


    public static UASparser uasParser = null;

    // 初始化uasParser对象
    static {
           try {
              uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
           } catch (IOException e) {
               e.printStackTrace();
           }
    }
}
