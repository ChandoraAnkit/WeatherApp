package com.testapp.chandora.androidy.weatherapp.data.weather.source;

import androidx.annotation.NonNull;

import com.testapp.chandora.androidy.weatherapp.data.weather.model.WeatherDetails;

/**
 * Created by chandora on 31-May-2019
 */
public class WeatherDataRepository implements WeatherDataSource {

    private static volatile WeatherDataRepository INSTANCE = null;

    private final WeatherDataSource mWeatherRemoteDataSource;

    private final WeatherDataSource mWeatherLocalDataSource;


    // Prevent direct instantiation.
    private WeatherDataRepository(@NonNull WeatherDataSource weatherRemoteDataSource,
                                  @NonNull WeatherDataSource weatherLocalDataSource) {
        mWeatherRemoteDataSource = weatherRemoteDataSource;
        mWeatherLocalDataSource = weatherLocalDataSource;
    }


    public static WeatherDataRepository getInstance(@NonNull WeatherDataSource weatherRemoteDataSource,
                                                    @NonNull WeatherDataSource weatherLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new WeatherDataRepository(weatherRemoteDataSource, weatherLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getWeatherDetailsFromServer(String query, OnWeatherDetailsLoadedCallback callback) {
        mWeatherRemoteDataSource.getWeatherDetailsFromServer(query, new OnWeatherDetailsLoadedCallback() {
            @Override
            public void onSuccessDataLoaded(WeatherDetails weather) {
                callback.onSuccessDataLoaded(weather);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void getWeatherDetailsFromLocal(OnWeatherDetailsLoadedCallback weatherCallback) {
        mWeatherLocalDataSource.getWeatherDetailsFromLocal(new OnWeatherDetailsLoadedCallback() {
            @Override
            public void onSuccessDataLoaded(WeatherDetails weatherDetails) {
                weatherCallback.onSuccessDataLoaded(weatherDetails);
            }

            @Override
            public void onError() {
                weatherCallback.onError();
            }
        });
    }

    @Override
    public void savedWeatherDetailsLocally(WeatherDetails weatherDetails) {
           mWeatherLocalDataSource.savedWeatherDetailsLocally(weatherDetails);
    }
}
