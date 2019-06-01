package com.testapp.chandora.androidy.weatherapp.data.weather.source.remote;

import com.testapp.chandora.androidy.weatherapp.data.weather.model.CurrentWeather;
import com.testapp.chandora.androidy.weatherapp.data.weather.model.ForecastWeather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chandora on 31-May-2019
 */
public interface WeatherDataService {

    @GET("weather")
    Observable<CurrentWeather> fetchCurrentWeather(@Query("appid") String apiKey, @Query("q") String query);

    @GET("forecast")
    Observable<ForecastWeather> fetchForecastWeather(@Query("appid") String apiKey, @Query("q") String query, @Query("cnt") String count);
}
