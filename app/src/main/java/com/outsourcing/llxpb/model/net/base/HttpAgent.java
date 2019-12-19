package com.outsourcing.llxpb.model.net.base;


import com.outsourcing.llxpb.model.net.base.callback.ICallback;
import com.outsourcing.llxpb.model.net.base.processor.IHttpProcessor;
import com.outsourcing.llxpb.model.net.base.request.IRequest;

public class HttpAgent implements IHttpProcessor {
    public static String baseURL;
    private static HttpAgent instance;
    private static IHttpProcessor processor;
    private HttpAgent() {
    }
    public static void init(IHttpProcessor iHttpProcessor,String base){
        processor = iHttpProcessor;
        baseURL = base;
    }

    public static void setBaseURL(String baseURL) {
        HttpAgent.baseURL = baseURL;
    }

    public static HttpAgent obtain(){
        if(baseURL ==null)
            throw new IllegalStateException("HttpAgent has not be initialized,you should provide the baseURL!");
        if(instance==null){
            synchronized (HttpAgent.class){
                if(instance==null){
                    instance = new HttpAgent();
                }
            }
        }
        return instance;
    }

    @Override
    public void post(IRequest request, ICallback callback) {
        processor.post(request,callback);
    }

    @Override
    public void get(IRequest request, ICallback callback) {
        processor.get(request,callback);
    }
}
