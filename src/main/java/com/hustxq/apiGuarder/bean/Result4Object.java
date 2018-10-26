package com.hustxq.apiGuarder.bean;

public class Result4Object {
    private int code = 200;
    private String message = "success";
    private Object resultData;

    public int getCode() {
        return code;
    }

    public Result4Object setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result4Object setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResultData() {
        return resultData;
    }

    public Result4Object setResultData(Object resultData) {
        this.resultData = resultData;
        return this;
    }
}
