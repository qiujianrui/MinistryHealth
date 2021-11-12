package com.library.basemodule.net;

/**
 * <pre>
 *     author:
 *     time  : 2018/5/15
 *     desc  :
 * </pre>
 */

public class ApiException extends RuntimeException {

    private int code;
    private String msg;

    public ApiException() {
    }

    public ApiException(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
