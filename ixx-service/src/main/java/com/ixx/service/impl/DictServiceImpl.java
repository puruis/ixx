package com.ixx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ixx.dao.DictDao;
import com.ixx.entity.sys.DictDo;
import com.ixx.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:37
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictDao, DictDo> implements DictService {

    @Autowired
    DictDao dictDao;

}
