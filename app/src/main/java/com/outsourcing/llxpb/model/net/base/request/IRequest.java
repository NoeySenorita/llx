package com.outsourcing.llxpb.model.net.base.request;

import java.util.Map;

public interface IRequest {
    String url(RequestType type);
    Map<String,Object> body();
    String tag();
    enum RequestType{
        GET,
        POST
    }
}
