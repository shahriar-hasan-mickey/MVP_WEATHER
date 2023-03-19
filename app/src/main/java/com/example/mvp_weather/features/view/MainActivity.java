package com.example.mvp_weather.features.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp_weather.common.RequestCompleteListener;
import com.example.mvp_weather.databinding.ActivityMainBinding;
import com.example.mvp_weather.features.model.WeatherInfoModel;
import com.example.mvp_weather.features.model.WeatherInfoModelImpl;
import com.example.mvp_weather.features.model.data.City;
import com.example.mvp_weather.features.model.data.WeatherDataModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    private ActivityMainBinding binding;

    WeatherInfoModelImpl model;

//    private List<City> cityList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WeatherInfoModel model = new WeatherInfoModelImpl(getApplicationContext());

        model.getCityList(new RequestCompleteListener<List<City>>() {
            @Override
            public void onRequestSuccess(List<City> data) {
                onCityListFetchSuccess(data);
            }

            @Override
            public void onRequestFailed(String errorMessage) {
                onCityListFetchFailure(errorMessage);
            }
        });

        binding.layoutInput.btnViewWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.outputGroup.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void handleProgressBarVisibility(int visibility) {

    }

    @Override
    public void onCityListFetchSuccess(List<City> cityList) {
//        this.cityList = cityList;
        ArrayAdapter<String> cityAdapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                convertToListOfCityName(cityList)
        );
        binding.layoutInput.spinner.setAdapter(cityAdapter);
    }

//    TODO : EXTRA
    private List<String> convertToListOfCityName(List<City> cityList){
        ArrayList<String> cityNameList = new ArrayList<>();
        for (City city : cityList){
            cityNameList.add(city.getName());
        }
        return cityNameList;
    }



    @Override
    public void onCityListFetchFailure(String errorMessage) {

    }

    @Override
    public void onWeatherInfoFetchSuccess(WeatherDataModel weatherDataModel) {

    }

    @Override
    public void onWeatherInfoFetchFailure(String errorMessage) {

    }
}
