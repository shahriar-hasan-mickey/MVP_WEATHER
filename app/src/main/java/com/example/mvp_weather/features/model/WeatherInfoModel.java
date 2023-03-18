package com.example.mvp_weather.features.model;

import com.example.mvp_weather.common.RequestCompleteListener;
import com.example.mvp_weather.features.model.data.City;
import com.example.mvp_weather.features.model.data.WeatherInfoResponse;

import java.util.List;

public interface WeatherInfoModel {
    public void getCityList(RequestCompleteListener<List<City>> callback);
    public void getWeatherInformation(int cityId , RequestCompleteListener<WeatherInfoResponse> callback);
}
