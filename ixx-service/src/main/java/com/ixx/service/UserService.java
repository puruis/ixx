package com.ixx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ixx.entity.sys.UserDo;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-12
 * Time: 14:50
 */
public interface UserService extends IService<UserDo> {
    UserDo getUser(String userId);
}
