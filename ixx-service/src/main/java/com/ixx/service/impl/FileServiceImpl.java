package com.ixx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ixx.dao.FileDao;
import com.ixx.entity.oss.FileDo;
import com.ixx.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:37
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileDao, FileDo> implements FileService {

    @Autowired
    FileDao fileDao;

}
