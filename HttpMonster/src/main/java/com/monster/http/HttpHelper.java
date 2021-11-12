package com.monster.http;

public class HttpHelper {

    private HttpHelper() {}
    private volatile static HttpHelper instance;
    public static HttpHelper getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (HttpHelper.class) {
                if (instance == null) {
                    instance = new HttpHelper();
                }
            }
        }
        return instance;
    }

    public static void setBuild(Config config) {
        Builder.getInstance().setConfig(config);
    }

    public static Config getBuild() {
        return Builder.getInstance().getConfig();
    }

    public <T> void send(AbsRequestPage<T> requestPage) {
    }

}
