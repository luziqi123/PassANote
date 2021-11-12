package com.longface.passanote.http;

import com.monster.http.ParamsOfKV;

public class GetPM25 extends GetRequest {

    @Override
    public String getUrl() {
        return "common/air/getCityPM25Detail";
    }

    @Override
    public ParamsOfKV getHeaders() {
        return new ParamsOfKV().add("" , "");
    }

    @Override
    public ParamsOfKV getUrlParams() {
        return super.getUrlParams();
    }

    @Override
    protected void onSuccess(int code, Object data) {

    }

    @Override
    protected void onFail(int code, String errorMsg) {

    }


}
