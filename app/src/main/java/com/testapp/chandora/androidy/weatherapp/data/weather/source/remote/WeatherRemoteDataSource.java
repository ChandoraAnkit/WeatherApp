package com.testapp.chandora.androidy.weatherapp.data.weather.source.remote;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.testapp.chandora.androidy.weatherapp.Config;
import com.testapp.chandora.androidy.weatherapp.data.weather.model.CurrentWeather;
import com.testapp.chandora.androidy.weatherapp.data.weather.model.ForecastWeather;
import com.testapp.chandora.androidy.weatherapp.data.weather.model.WeatherDetails;
import com.testapp.chandora.androidy.weatherapp.data.weather.source.WeatherDataSource;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chandora on 31-May-2019
 */
public class WeatherRemoteDataSource implements WeatherDataSource {

    private static volatile WeatherRemoteDataSource INSTANCE = null;

    private WeatherDataService weatherApi;


    // Prevent direct instantiation.
    private WeatherRemoteDataSource() {
    }


    public static WeatherRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WeatherRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getWeatherDetailsFromServer(String query, OnWeatherDetailsLoadedCallback callback) {

        Observable<CurrentWeather> currentWeather = getWeatherApi().fetchCurrentWeather(Config.API_KEY, query);
        currentWeather.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<ForecastWeather> forecastWeather = getWeatherApi().fetchForecastWeather(Config.API_KEY, query,"10");
        forecastWeather.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<WeatherDetails> combinedResult = Observable.zip(currentWeather
                , forecastWeather
                , (currentWeather1, forecastWeather1) -> {

                    WeatherDetails weatherDetails = new WeatherDetails();
                    weatherDetails.setCurrentWeather(currentWeather1);
                    weatherDetails.setForecastWeather(forecastWeather1);

                    return weatherDetails;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        combinedResult.subscribe(new Observer<WeatherDetails>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WeatherDetails weatherDetails) {
                callback.onSuccessDataLoaded(weatherDetails);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError();
            }

            @Override
            public void onComplete() {

            }
        });


    }

    @Override
    public void getWeatherDetailsFromLocal(OnWeatherDetailsLoadedCallback weatherCallback) {
            //Not applicable here
    }

    @Override
    public void savedWeatherDetailsLocally(WeatherDetails weatherDetails) {
        //Not applicable here
    }


    public WeatherDataService getWeatherApi() {
        if (weatherApi == null) createNewsApi();
        return weatherApi;
    }

    private void createNewsApi() {
        weatherApi = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create((WeatherDataService.class));
    }
}
