package com.ixx.controller.oss;

import com.ixx.common.result.ResultJson;
import com.ixx.entity.oss.FileDo;
import com.ixx.service.FileService;
import com.ixx.util.PaiFileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
/**
 * Description:
 * User: purui_zhang
 * Date: 2018-12-22
 * Time: 21:58
 */
@Slf4j
@Controller
@RequestMapping(value = "oss")
public class OssController {

    @Value("${oss.youpai.uploadDir}")
    private String uploadDir;
    @Autowired
    private FileService fileService;

    @RequiresPermissions("oss:paiUploadFile")
    @ResponseBody
    @PostMapping(value = "paiUploadFile")
    public Object paiUploadFile(@RequestParam("file")MultipartFile file){
        // 上传到OSS
        String resultUrl = null;
        try {
            String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            String fileName = UUID.randomUUID().toString();
            File file1 = File.createTempFile(fileName, type);
            file.transferTo(file1);
            resultUrl = PaiFileUploadUtils.uploadFile(uploadDir, file1);

            FileDo fileDo = new FileDo();
            fileDo.setFileName(fileName);
            fileDo.setFileOriginalName(file.getOriginalFilename());
            fileDo.setFileSize(String.valueOf(file1.length()));
            fileDo.setFileType(type);
            fileDo.setUrl(resultUrl);
            fileService.save(fileDo);
        }catch (Exception e){
            log.error("又拍云文件上传失败:{}",e );
        }
        return ResultJson.success("http://"+resultUrl);
    }
}
