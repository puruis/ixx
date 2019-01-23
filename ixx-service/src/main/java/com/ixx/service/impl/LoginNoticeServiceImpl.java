package com.ixx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ixx.dao.LoginNoticeDao;
import com.ixx.entity.sys.LoginNoticeDo;
import com.ixx.service.LoginNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:37
 */
@Service
public class LoginNoticeServiceImpl extends ServiceImpl<LoginNoticeDao, LoginNoticeDo> implements LoginNoticeService{

    @Autowired
    LoginNoticeDao loginNoticeDao;

}
