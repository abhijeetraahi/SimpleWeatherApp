package com.raahi.simpleweatherapp.api;

import com.raahi.simpleweatherapp.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Raahi on 05-07-2018.
 */

public interface ApiInterface {
    @Headers("Content-Type: application/json")
    @GET("forecast.json")
    Call<WeatherResponse> getWeather(@Query("key") String key, @Query("q") String city, @Query("days") String days);
}
