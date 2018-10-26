package com.hustxq.apiGuarder.permission;

/**
 * @author: qxiong
 * @date: 2018/10/24
 * @description:
 */
public class PermissionException extends RuntimeException {
    public PermissionException(String msg){
        super(msg);
    }
}
