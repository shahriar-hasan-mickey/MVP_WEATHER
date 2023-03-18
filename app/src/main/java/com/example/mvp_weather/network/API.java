package com.example.mvp_weather.network;

import android.graphics.ColorSpace;

import com.example.mvp_weather.features.model.data.WeatherInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("weather")
    Call<WeatherInfoResponse> weatherInfoResponse(@Query("q") String cityName, @Query("appid") String APP_ID);

}
