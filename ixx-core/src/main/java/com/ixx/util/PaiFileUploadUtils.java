package com.ixx.util;

import com.UpYun;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.UUID;

/**
 * Description: 又拍云 储存文件上传
 * User: purui_zhang
 * Date: 2018-12-22
 * Time: 20:38
 */
@Slf4j
public class PaiFileUploadUtils {

    private static final String BUCKETNAME = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final String HTTPBASE = "";


    /**
     *  上传文件
     * @param folder
     * @param fileName
     * @param file
     * @return
     */
    public static String uploadFile(String folder,String fileName,File file){
        UpYun upYun = new UpYun(BUCKETNAME, USERNAME, PASSWORD);
        upYun.setTimeout(60);
        //选择最优的接入点
        upYun.setApiDomain(UpYun.ED_AUTO);
        boolean result = false;
        try{
            result = upYun.writeFile(folder+fileName, file, true);
            if(result){
                log.info("上传状态{}", result);
            }
        }catch (Exception e){
            log.error("又拍云上传文件失败:{}",e);
        }
        return HTTPBASE+folder+fileName;
    }

    /**
     *  上传文件
     * @param folder
     * @param file
     * @return
     */
    public static String uploadFile(String folder,File file){
        String fileName = UUID.randomUUID().toString();
        String uploadFile = uploadFile(folder, fileName, file);
        return uploadFile;
    }

    /**
     *  文件删除
     * @param filePath
     * @return
     */
    public static Boolean deleteFile(String filePath){
        UpYun upYun = new UpYun(BUCKETNAME, USERNAME, PASSWORD);
        try {
            boolean b = upYun.deleteFile(filePath);
            return b;
        } catch (Exception e) {
            log.error("又拍云文件删除失败:{}",e );
        }
        return false;
    }
}
