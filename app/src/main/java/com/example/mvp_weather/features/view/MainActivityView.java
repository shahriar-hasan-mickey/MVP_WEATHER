package com.example.mvp_weather.features.view;

import com.example.mvp_weather.features.model.data.City;
import com.example.mvp_weather.features.model.data.WeatherDataModel;

import java.util.List;

public interface MainActivityView {

    public void handleProgressBarVisibility(int visibility);
    public void onCityListFetchSuccess(List<City> cityList);
    public void onCityListFetchFailure(String errorMessage);
    public void onWeatherInfoFetchSuccess(WeatherDataModel weatherDataModel);
    public void onWeatherInfoFetchFailure(String errorMessage);
}
