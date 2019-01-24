package com.ixx.service;

import com.UpYun;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.UUID;
/**
 * Description:文件上传服务
 * User: purui_zhang
 * Date: 2019-01-24
 * Time: 15:40
 */
@Component
@Slf4j
public class FileUploadService {
    @Value("#{oss.youpai.bucketName}")
    private String bucketName;
    @Value("#{oss.youpai.userName}")
    private String userName;
    @Value("#{oss.youpai.password}")
    private String password;
    @Value("#{oss.youpai.httpBase}")
    private String httpBase;


    /**
     *  上传文件
     * @param folder
     * @param fileName
     * @param file
     * @return
     */
    public String uploadFile(String folder,String fileName,File file){
        UpYun upYun = new UpYun(bucketName, userName, password);
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
        return httpBase+folder+fileName;
    }

    /**
     *  上传文件
     * @param folder
     * @param file
     * @return
     */
    public String uploadFile(String folder,File file){
        String fileName = UUID.randomUUID().toString();
        String uploadFile = uploadFile(folder, fileName, file);
        return uploadFile;
    }

    /**
     *  文件删除
     * @param filePath
     * @return
     */
    public Boolean deleteFile(String filePath){
        UpYun upYun = new UpYun(bucketName, userName, password);
        try {
            boolean b = upYun.deleteFile(filePath);
            return b;
        } catch (Exception e) {
            log.error("又拍云文件删除失败:{}",e );
        }
        return false;
    }
}
