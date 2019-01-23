package com.ixx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ixx.dao.UserDao;
import com.ixx.entity.sys.UserDo;
import com.ixx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-12
 * Time: 14:50
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserDo> implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public UserDo getUser(String userId) {
       return userDao.selectById(userId);
    }
}
