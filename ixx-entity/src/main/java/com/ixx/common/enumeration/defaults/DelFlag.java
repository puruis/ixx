package com.ixx.common.enumeration.defaults;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-27
 * Time: 17:53
 */
public enum DelFlag implements IEnum<Integer> {
    NORMAL(0,"正常"),
    DELETE(1,"删除");

    private Integer value;
    private String desc;

    DelFlag(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
