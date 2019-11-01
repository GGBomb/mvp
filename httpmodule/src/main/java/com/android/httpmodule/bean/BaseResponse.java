package com.android.httpmodule.bean;

/**
 * 所有接口返回结果统一格式
 * {
 * "status":200,
 * "message":"successful",
 * "data":{}
 * }
 */
public class BaseResponse<T> {
    public int status;
    public String message;
    public T data;//JSONObject

    public boolean isSuccess() {
        return status == 200;
    }
}
