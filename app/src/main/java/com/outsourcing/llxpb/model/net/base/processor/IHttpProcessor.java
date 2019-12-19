package com.outsourcing.llxpb.model.net.base.processor;


import com.outsourcing.llxpb.model.net.base.callback.ICallback;
import com.outsourcing.llxpb.model.net.base.request.IRequest;

public interface IHttpProcessor {
    void post(IRequest request, ICallback callback);
    void get(IRequest request, ICallback callback);
}
