package com.excellent.dm.net;

import java.util.Map;

/**
 * api请求返回信息
 */
public class ApiResult<T> implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String State;
    private String Msg;
    private T Obj;

    public boolean isSuccess() {
        return this.State.equals("200");
    }

    public void setState(String state) {
        State = state;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public T getObj() {
        return Obj;
    }

    public void setObj(T obj) {
        Obj = obj;
    }
}
