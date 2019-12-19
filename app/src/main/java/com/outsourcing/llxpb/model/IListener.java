package com.outsourcing.llxpb.model;

public interface IListener<RESPONSE> {
    void onComplete(RESPONSE response);
    void onError(Exception e);
}
