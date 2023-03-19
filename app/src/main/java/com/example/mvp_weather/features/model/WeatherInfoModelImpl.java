package com.example.mvp_weather.features.model;

import android.content.Context;
import android.util.Log;

import com.example.mvp_weather.common.RequestCompleteListener;
import com.example.mvp_weather.features.model.data.City;
import com.example.mvp_weather.features.model.data.WeatherInfoResponse;
import com.example.mvp_weather.network.API;
import com.example.mvp_weather.network.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherInfoModelImpl implements WeatherInfoModel{


    public String APP_ID = "b6907d289e10d714a6e88b30761fae22";


    Context context;
    public WeatherInfoModelImpl(Context applicationContext) {
        this.context = applicationContext;
    }

    //    TODO : https://www.bezkoder.com/java-android-read-json-file-assets-gson/
    @Override
    public void getCityList(RequestCompleteListener<List<City>> callback) {

        String jsonFileString = getJsonFromAssets(context);
        Log.i("data", jsonFileString);

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<City>>() { }.getType();

        List<City> cityList = gson.fromJson(jsonFileString, listUserType);
        for (int i = 0; i < cityList.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + cityList.get(i));
        }

//        callback.onRequestSuccess(cityList);
    }

    @Override
    public void getWeatherInformation(int cityId, RequestCompleteListener<WeatherInfoResponse> callback) {
        API api = new RetrofitClient().client().create(API.class);

        Call <WeatherInfoResponse> call = api.weatherInfoResponse("London", APP_ID);
        call.enqueue(new Callback<WeatherInfoResponse>() {
            @Override
            public void onResponse(Call<WeatherInfoResponse> call, Response<WeatherInfoResponse> response) {
                if(response.body() != null){
                    callback.onRequestSuccess(response.body());
                }else {
                    callback.onRequestFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherInfoResponse> call, Throwable t) {
                callback.onRequestFailed(t.getLocalizedMessage());
            }
        });
    }



    private static String getJsonFromAssets(Context context) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open("city_list.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
}

//https://stackoverflow.com/questions/42036278/how-to-pass-context-from-mainactivity-to-another-class-in-android
//https://www.dev2qa.com/android-get-application-context-from-anywhere-example/
//https://www.tabnine.com/code/java/methods/android.app.Activity/getApplicationContext