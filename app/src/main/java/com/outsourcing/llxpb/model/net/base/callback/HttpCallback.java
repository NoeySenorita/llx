package com.outsourcing.llxpb.model.net.base.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public  abstract class HttpCallback<RESULT> implements ICallback {
    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> cls = analysisClassInfo(this);
        RESULT res = (RESULT) gson.fromJson(result, cls);
        onSuccess(res);
    }
    

    public abstract void onSuccess(RESULT res);

    private Class<?> analysisClassInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }

}
