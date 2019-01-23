package com.ixx.entity.oss;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ixx.common.baseentity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Description: 文件
 * User: purui_zhang
 * Date: 2018-12-23
 * Time: 23:47
 */
@Getter
@Setter
@TableName("l_file")
public class FileDo extends BaseEntity{

    @TableId
    private String id;
    private String fileOriginalName;
    private String fileName;
    private String fileType;
    private String fileSize;
    private String url;
    private Date createTime;
    private String createUser;
}
