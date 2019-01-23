package com.ixx.util;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-07-04
 * Time: 18:09
 */
@Slf4j
public class HttpUtils {


    /**
     * get 请求
     *
     * @param path
     * @return
     */
    public static String getMethod(String path) {
        return getMethod(path, 6000, 6000);
    }

    /**
     * get 请求
     *
     * @param path
     * @param timeOut
     * @return
     */
    public static String getMethod(String path, Integer timeOut) {
        return getMethod(path, timeOut, 20000);
    }

    /**
     * get 请求
     *
     * @param path
     * @param timeOut
     * @param readTimeOut
     * @return
     */
    public static String getMethod(String path, Integer timeOut, Integer readTimeOut) {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(timeOut);
            conn.setReadTimeout(readTimeOut);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 设置文件类型:
            conn.setRequestProperty("contentType" , "x-www-form-urlencoded");
            //传输开始
            conn.connect();
            if (200 == conn.getResponseCode()) {
                //得到输入流
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        } catch (Exception e) {
            log.error("请求地址:{} thorw Exception:{}" , path, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post 请求
     *
     * @param path
     * @return
     */
    public static String postMethod(String path, String requestStr) {
        return postMethod(path, 6000*10*5, 6000*10*5, requestStr);
    }

    /**
     * post 请求
     *
     * @param path
     * @param timeOut
     * @return
     */
    public static String postMethod(String path, Integer timeOut, String requestStr) {
        return postMethod(path, timeOut, 6000, requestStr);
    }

    /**
     * post 请求
     *
     * @param path
     * @param timeOut
     * @param readTimeOut
     * @return
     */
    public static String postMethod(String path, Integer timeOut, Integer readTimeOut, String requestStr) {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(timeOut);
            conn.setReadTimeout(readTimeOut);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 设置文件类型:
            conn.setRequestProperty("contentType" , "x-www-form-urlencoded");
            //传输开始
            conn.connect();
            @Cleanup
            OutputStream out = conn.getOutputStream();
            log.info(" post 请求 请求发送报文:{}" , requestStr);
            out.write(requestStr.getBytes());
            out.flush();

            if (200 == conn.getResponseCode()) {
                //得到输入流
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        } catch (Exception e) {
            log.error("请求地址:{} thorw Exception:{}" , path, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
