package com.longface.jhttp;

import android.app.Application;

/**
 * 可以适配不同的核心请求库
 * 支持请求失败重发
 * 支持请求记录输出
 */
public class HttpSender {

    static Application application;

    public static void init(Application application) {
        HttpSender.application = application;
    }

    public static void baseUrl(String baseUrl) {

    }


}
