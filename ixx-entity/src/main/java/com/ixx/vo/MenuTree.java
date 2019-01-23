package com.ixx.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-15
 * Time: 16:51
 */
@Setter
@Getter
public class MenuTree {
    private String id;
    private String pid;
    private String name;
    private String authority;
    private String icon;
    private List<MenuTree> list;
    private String url;
    private Integer type;
    private Integer order;
    private List<MenuTree> children;

}
