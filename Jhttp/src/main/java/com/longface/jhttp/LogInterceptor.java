package com.longface.jhttp;

import android.app.Application;
import android.content.pm.ApplicationInfo;

import com.longface.simpleutils.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

public class LogInterceptor implements Interceptor {

    private String TAG = "HttpLog";
    private final Charset UTF8 = Charset.forName("UTF-8"); //urf8编码
    private boolean isRelease = true;

    public LogInterceptor(Application application) {
        isRelease = (application.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    public LogInterceptor() {
        isRelease = false;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        String acid = request.url().queryParameter("ACID");
        Response response;
        try {
            long startTime = System.nanoTime();
            response = chain.proceed(request);
            long duration = System.nanoTime() - startTime;
            String method = request.method();
            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Logger.put(TAG , request.url().host());
            Logger.put(TAG , method);
            Logger.put(TAG , duration + "");
            Logger.put(TAG , bodyToString(request.body()));
            Logger.out(TAG , buffer.clone().readString(UTF8));
        } catch (Exception e) {
            Logger.put(TAG , request.url().host());
            Logger.put(TAG , "请求异常!");
            Logger.out(TAG , e.getMessage() + "");
            e.printStackTrace();
            throw e;
        }
        return response;
    }

    private String bodyToString(final RequestBody request) {

        try {
            final Buffer buffer = new Buffer();
            request.writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
