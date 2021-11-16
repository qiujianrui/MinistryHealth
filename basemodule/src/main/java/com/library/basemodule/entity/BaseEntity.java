package com.library.basemodule.entity;

/**
  * @author jere
  * @time 2020/6/9
  * 说明：请求基类
  */

public class BaseEntity<T> {

     public static final int LOGOUT = 201; // 登录失效
    public static final int SUCCESS = 200; //正常返回码
    private int code;
    private T data;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk(int statusCode) {
        return SUCCESS==statusCode;
    }

    public boolean isOk() {
        return SUCCESS== code;
    }


    public int getCode() {
        return code;
    }

    public BaseEntity setCode(int code) {
        this.code = code;
        return this;
    }



    public String getMsg() {
        return msg;
    }

    public BaseEntity setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
