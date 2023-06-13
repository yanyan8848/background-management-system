package com.hzlx.utils;

import com.google.gson.Gson;

public class BaseResult<T> {
    private Integer code;
    private String mag;
    private T data;
    private BaseResult(){}
    private BaseResult(Integer code,String mag,T data){
        this.code=code;
        this.mag=mag;
        this.data=data;
    }

    public static String success() {
        Gson gson=new Gson();
        return gson.toJson(new BaseResult(200, "success", null));
    }

    public static <T> String success(T data) {
        Gson gson=new Gson();
        return gson.toJson(new BaseResult(200, "success", data));
    }

    public static String error(Integer code, String mag) {
        Gson gson=new Gson();
        return gson.toJson(new BaseResult(code, mag, null));
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
