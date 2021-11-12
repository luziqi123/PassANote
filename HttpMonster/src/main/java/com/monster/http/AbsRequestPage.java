package com.monster.http;

import androidx.core.util.Pair;

import java.util.ArrayList;

public abstract class AbsRequestPage<T> {

//    private ArrayList<Pair<String, String>> headers;
//
//    private ArrayList<Pair<String, String>> urlParams;
//
//    private ArrayList<Pair<String, Object>> params;

    public abstract Method getMethod();

    public abstract String getBaseUrl();

    public abstract String getUrl();

    public abstract ContentType getContentType();

    public abstract ParamsOfKV getHeaders();

    public abstract ParamsOfKV getUrlParams();

    public abstract ArrayList<Pair<String, Object>> getBody();

    protected abstract void onSuccess(int code , T data);

    protected abstract void onFail(int code , String errorMsg);


//    public AbsRequestPage addHeaders(String key , String value) {
//        if (headers == null) {
//            headers = new ArrayList<>();
//        }
//        headers.add(new Pair<>(key, value));
//        return this;
//    }
//
//
//    public AbsRequestPage addParams(String key , Object value) {
//        if (params == null) {
//            params = new ArrayList<>();
//        }
//        params.add(new Pair<>(key, value));
//        return this;
//    }
//
//    public AbsRequestPage addUrlParams(String key , String value) {
//        if (urlParams == null) {
//            urlParams = new ArrayList<>();
//        }
//        urlParams.add(new Pair<>(key, value));
//        return this;
//    }

}