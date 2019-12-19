package com.outsourcing.llxpb.model.net.base.request;


import com.outsourcing.llxpb.model.net.base.HttpAgent;

import java.util.Map;

public abstract class BaseRequest implements IRequest{
    RequestBuilder builder = new RequestBuilder();
    @Override
    public String url(RequestType type) {
        builder.setURL(HttpAgent.baseURL,provideURI());
        if(type==RequestType.GET){
            addParam(builder);
        }
        return builder.build();
    }

    @Override
    public Map<String, Object> body() {
        addParam(builder);
        return builder.getmParams();
    }

    @Override
    public String tag() {
        return provideURI();
    }

    public abstract void addParam(RequestBuilder builder);
    public abstract String provideURI();
}
