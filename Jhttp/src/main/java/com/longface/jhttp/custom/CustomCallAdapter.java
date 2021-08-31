package com.longface.jhttp.custom;

import com.longface.jhttp.R;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;

public class CustomCallAdapter implements CallAdapter<R, CustomCall<R>> {

    private final Type responseType;

    public CustomCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return null;
    }

    @Override
    public CustomCall<R> adapt(Call<R> call) {
        return new CustomCall<>(call);
    }
}
