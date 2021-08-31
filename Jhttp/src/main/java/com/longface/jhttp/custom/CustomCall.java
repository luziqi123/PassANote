package com.longface.jhttp.custom;

import java.io.IOException;

import retrofit2.Call;

public class CustomCall<R> {

    public final Call<R> call;

    public CustomCall(Call<R> call) {
        this.call = call;
    }

    public R get() {
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
