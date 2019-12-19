package com.outsourcing.llxpb.model.net.base.request;

import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {
    private StringBuilder mSb;
    private HashMap<String,Object> mParams;
    private boolean hasURL;

    public RequestBuilder() {
        this.mSb = new StringBuilder();
        this.mParams = new HashMap<>();
    }
    protected RequestBuilder setURL(String base,String uri) {
        if (!hasURL) {
            mSb.append(base).append(uri);
            hasURL = true;
        }
        return this;
    }
    public RequestBuilder addParam(String key, Object value) {
        if (mParams.containsKey(key)) return this;
        mParams.put(key, value);
        return this;
    }

    public String build() {
        String urlParamsByMap = getUrlParamsByMap(mParams);
        mSb.append(urlParamsByMap);
        return mSb.toString();
    }

    public HashMap<String, Object> getmParams() {
        return mParams;
    }

    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        if(map.size()!=0){
            sb.append("?");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue());
                sb.append("&");
            }
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }
}
