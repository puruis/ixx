package com.ixx.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Description: EleTree 实体
 * User: purui_zhang
 * Date: 2018-12-05
 * Time: 22:58
 */
@Getter
@Setter
public class EleMenuTree implements Serializable{
    private String id;
    private String label;
    private Boolean disabled;
    private Boolean isLeaf;
    private Boolean checked = true;
    private List<EleMenuTree> children;
}
