package com.monster.http;

import com.google.gson.Gson;
import com.monster.http.request.GetRequest;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class Config {

    private OkHttpClient okHttpClient;

    private Gson gson;

    private HashMap<Method, IRealRequest<?>> realRequestHashMap;

    public Config() {
        // 创建默认的OkHttpClient
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2000, TimeUnit.MILLISECONDS)
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();

        // 创建默认的Gson
        gson = new Gson();

        // 创建默认的请求类
        realRequestHashMap = new HashMap<>();
        realRequestHashMap.put(Method.GET, new GetRequest());

    }

    public HashMap<Method, IRealRequest<?>> getRealRequestHashMap() {
        return realRequestHashMap;
    }

    public Config setRealRequestHashMap(HashMap<Method, IRealRequest<?>> realRequestHashMap) {
        this.realRequestHashMap = realRequestHashMap;
        return this;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Config setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return this;
    }

    public Gson getGson() {
        return gson;
    }

    public Config setGson(Gson gson) {
        this.gson = gson;
        return this;
    }

}
