package com.testapp.chandora.androidy.weatherapp.data.weather.model;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by chandora on 31-May-2019
 */

@Entity
public class WeatherDetails {

    @PrimaryKey
    public int key = 0;

    @Embedded
    private CurrentWeather currentWeather;

    @Embedded
    private ForecastWeather forecastWeather;

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public ForecastWeather getForecastWeather() {
        return forecastWeather;
    }

    public void setForecastWeather(ForecastWeather forecastWeather) {
        this.forecastWeather = forecastWeather;
    }
}
