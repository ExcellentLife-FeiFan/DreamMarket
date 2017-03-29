package com.excellent.dm.net;

import java.util.Map;

/**
 * api请求返回信息
 */
public class ApiResult<T> implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success = true;
    private int code = 200;
    private String msg = "操作成功";
    private String title = "成功";
    private T result;
    private Map<String, Object> resultMap;// 其他参数

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
