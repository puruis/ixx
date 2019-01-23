package com.ixx.vo;

/**
 * Description:
 * User: purui_zhang
 * Date: 2018-12-14
 * Time: 18:11
 */
public class ZTree<T> {
    /**
     * 节点ID
     */
    private String id;
    private String pId;
    /**
     * 显示节点文本
     */
    private String name;

    private Boolean checked;
    private Boolean open;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
