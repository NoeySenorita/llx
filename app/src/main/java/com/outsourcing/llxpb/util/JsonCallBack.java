package com.outsourcing.llxpb.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.outsourcing.llxpb.util.ui.UIUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class JsonCallBack<T> extends AbsCallback<T> {
    private Type type;
    private Class<T> clazz;

    public JsonCallBack() {
    }

    public JsonCallBack(Type type) {
        this.type = type;
    }

    public JsonCallBack(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertSuccess(Response response) throws Exception {
        ResponseBody body = response.body();
        if(body == null) return null;
        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if(type != null){
            data = gson.fromJson(jsonReader,type);
        }else if(clazz != null){
            data = gson.fromJson(jsonReader,clazz);
        }else{
            Type genType = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType)genType).getActualTypeArguments()[0];
            data = gson.fromJson(jsonReader,type);
        }
        return data;
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);
        UIUtils.showToast(e.getLocalizedMessage());
        /*try {
            String s = response.body().string();
            UIUtils.showToast(response.body().string());
        } catch (IOException e1) {
            e1.printStackTrace();
        }*/
    }
}
