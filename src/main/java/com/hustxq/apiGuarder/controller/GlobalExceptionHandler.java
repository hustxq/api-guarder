package com.hustxq.apiGuarder.controller;

import com.hustxq.apiGuarder.bean.Result4Object;
import com.hustxq.apiGuarder.permission.PermissionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qxiong on 2018/4/13.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result4Object defaultHandler(HttpServletRequest request, Exception e){
        Result4Object res = new Result4Object();
        res.setMessage(e.getMessage());
        if (e instanceof NoHandlerFoundException) {
            res.setCode(404);
        } else if (e instanceof PermissionException) {
            res.setCode(403);
        } else {
            res.setCode(500);
        }
        Map<String, String> mv = new HashMap<>();
        mv.put("url", request.getRequestURI());
        res.setResultData(mv);
        return res;
    }
}
