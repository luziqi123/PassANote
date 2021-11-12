package com.longface.passanote.http;

import androidx.core.util.Pair;

import com.monster.http.ContentType;
import com.monster.http.Method;
import com.monster.http.ParamsOfKV;

import java.util.ArrayList;

public abstract class GetRequest extends BaseRequest {

    @Override
    public ParamsOfKV getUrlParams() {
        return new ParamsOfKV().add("key" , BaseRequest.key);
    }

    @Override
    public final Method getMethod() {
        return Method.GET;
    }

    @Override
    public ParamsOfKV getHeaders() {
        return null;
    }

    @Override
    public final ArrayList<Pair<String, Object>> getBody() {
        return null;
    }

    @Override
    public final ContentType getContentType() {
        return null;
    }

}
