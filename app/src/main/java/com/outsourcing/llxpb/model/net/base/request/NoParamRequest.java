package com.outsourcing.llxpb.model.net.base.request;

public class NoParamRequest extends BaseRequest {
    String uri;

    public NoParamRequest(String uri) {
        this.uri = uri;
    }

    @Override
    public void addParam(RequestBuilder builder) {

    }

    @Override
    public String provideURI() {
        return uri;
    }
}
