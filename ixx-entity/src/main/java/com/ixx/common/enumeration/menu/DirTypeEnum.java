package com.ixx.common.enumeration.menu;

import com.baomidou.mybatisplus.core.enums.IEnum;
/**
 * Description: 菜单类型
 * User: purui_zhang
 * Date: 2018-11-13
 * Time: 17:25
 */
public enum DirTypeEnum implements IEnum<Integer>{
    DIR(0,"目录"),
    MENU(1,"菜单"),
    BUTTON(2,"按钮");

    private Integer value;
    private String desc;

    DirTypeEnum(Integer value, String desc) {
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
