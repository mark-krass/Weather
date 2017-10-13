package com.example.gambm.weather;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface WeatherService {
    @GET("v1/public/yql")
    Call<ArrayList<Info>> repoContributors(
            @Query("q") String q);


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://query.yahooapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}

