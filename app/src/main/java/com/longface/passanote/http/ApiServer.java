package com.longface.passanote.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServer {

    @GET("common/air/getCityPM25Detail")
    Call<String> getPM25(@Query("key") String key , @Query("city") String city);

}
