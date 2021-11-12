package com.monster.http.request;

import com.monster.http.AbsRequestPage;
import com.monster.http.HttpHelper;
import com.monster.http.IRealRequest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetRequest implements IRealRequest {

    @Override
    public void request(AbsRequestPage requestPage) {
        OkHttpClient okHttpClient = HttpHelper.getBuild().getOkHttpClient();
        Request build = new Request.Builder()
                .get()
                .url(requestPage.getUrl())
                .build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
