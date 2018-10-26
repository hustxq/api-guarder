package com.hustxq.apiGuarder.permission;

/**
 * @author: qxiong
 * @date: 2018/10/24
 * @description:
 */
public enum Permission {
    /**
     * 业务操作员
     */
    OPERATOR("OPERATOR"),
    /**
     * 管理员
     */
    ADMIN("ADMIN");

    private String v;
    private Permission(String v){
        this.v = v;
    }

    public String getV() {
        return v;
    }
}
