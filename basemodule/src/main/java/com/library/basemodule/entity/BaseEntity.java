package com.library.basemodule.entity;

import java.io.Serializable;

/**
  * @author jere
  * @time 2020/6/9
  * 说明：请求基类
  */

public class BaseEntity  {

     public static final int LOGOUT_1 = 1; // 登录失效
    public static final int LOGOUT_2 = 2; // 其他地方登陆
    public static final int SUCCESS = 0; //正常返回码
    private int Ret;

    private String ErrMsg;



    public boolean isOk(int statusCode) {
        return SUCCESS==statusCode;
    }

    public boolean isOk() {
        return SUCCESS== Ret;
    }


    public int getRet() {
        return Ret;
    }

    public BaseEntity setRet(int ret) {
        this.Ret = ret;
        return this;
    }



    public String getErrMsg() {
        return ErrMsg;
    }

    public BaseEntity setErrMsg(String errMsg) {
        this.ErrMsg = errMsg;
        return this;
    }
}
