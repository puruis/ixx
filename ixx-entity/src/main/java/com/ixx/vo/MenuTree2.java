package com.ixx.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-22
 * Time: 17:03
 */
@Getter
@Setter
public class MenuTree2 {
    private String id;
    private String name;
    private Boolean spread;
    private List<MenuTree2> children;
}
