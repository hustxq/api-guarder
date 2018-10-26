package com.hustxq.apiGuarder.controller;

import com.hustxq.apiGuarder.bean.Result4Object;
import com.hustxq.apiGuarder.permission.Guarder;
import com.hustxq.apiGuarder.permission.Permission;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Guarder
@RestController
public class DemoController {

    @Guarder(name = Permission.ADMIN)
    @RequestMapping("/admin")
    public Object admin(HttpServletRequest request) {
        return new Result4Object().setMessage("admin allowed.");
    }

    @RequestMapping("/opt")
    public Object opt(HttpServletRequest request) {
        return new Result4Object().setMessage("operator allowed.");
    }

}
