package com.excellent.dmu.net;



import com.dm.excellent.baselibrary.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.BaseRequest;

import java.lang.reflect.Type;

import okhttp3.Response;

public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Class<T> clazz;
    private Type type;

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //主要用于在所有请求之前添加公共的请求头或请求参数，例如登录授权的 token,使用的设备信息等,可以随意添加,也可以什么都不传
//        request.params("token", "3215sdf13ad1f65asd4f3ads1f");
    }

    @Override
    public T convertSuccess(Response response) throws Exception {
        String data = null;
        try {
            data = response.body().string();
            LogUtils.e(data);
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();

            if (clazz == String.class) return (T) data;
            try {
                if (clazz != null) return gson.fromJson(data, clazz);
                if (type != null) return gson.fromJson(data, type);
            } catch (JsonSyntaxException ex) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}