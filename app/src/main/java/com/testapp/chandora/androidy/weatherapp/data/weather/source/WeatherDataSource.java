package com.testapp.chandora.androidy.weatherapp.data.weather.source;

import com.testapp.chandora.androidy.weatherapp.data.weather.model.WeatherDetails;

/**
 * Created by chandora on 31-May-2019
 */
public interface WeatherDataSource {

    interface OnWeatherDetailsLoadedCallback{

        void onSuccessDataLoaded(WeatherDetails weatherDetails);

        void onError();
    }


    void getWeatherDetailsFromServer(String query, OnWeatherDetailsLoadedCallback callback);

    void getWeatherDetailsFromLocal(OnWeatherDetailsLoadedCallback weatherCallback);

    void savedWeatherDetailsLocally(WeatherDetails weatherDetails);
}
