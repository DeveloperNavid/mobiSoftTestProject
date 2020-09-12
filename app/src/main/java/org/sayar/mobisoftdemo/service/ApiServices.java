package org.sayar.mobisoftdemo.service;


import org.sayar.mobisoftdemo.model.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("/")
    Call<Object> getVideoList(@Query("apikey") String apiKey, @Query("s") String s);

    @GET("/")
    Call<Object> getVideo(@Query("i") String imdbId, @Query("apikey") String apiKey);

}
