package com.hustxq.apiGuarder.permission;

import java.lang.annotation.*;

/**
 * @author: qxiong
 * @date: 2018/10/24
 * @description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Guarder {
    Permission[] name() default Permission.OPERATOR;
}