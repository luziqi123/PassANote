package com.longface.jhttp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("v2/banners")
    Call<String> groupList();


    @GET("v2/random/category/{category}/type/{type}/count/{count}")
    Call<String> getGirls(@Path("category") String category , @Path("type") String type , @Path("count") String count);
}
