package com.testapp.chandora.androidy.weatherapp.data.weather.model;

import androidx.room.Embedded;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.testapp.chandora.androidy.weatherapp.data.weather.typeConverter.WeatherItemTypeConverter;

import java.util.List;

public class CurrentWeather {

    @SerializedName("visibility")
    private int visibility;

    @SerializedName("timezone")
    private int timezone;

    @Embedded
    @SerializedName("main")
    private Main main;

    @Embedded
    @SerializedName("clouds")
    private Clouds clouds;

    @Embedded
    @SerializedName("sys")
    private Sys sys;

    @SerializedName("dt")
    private int dt;

    @Embedded
    @SerializedName("coord")
    private CurrentWeatherCoord currentWeatherCoord;

    @TypeConverters(WeatherItemTypeConverter.class)
    @SerializedName("weather")
    private List<WeatherItem> weather;

    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private int cod;

    @SerializedName("id")
    private int id;

    @SerializedName("base")
    private String base;

    @Embedded
    @SerializedName("wind")
    private Wind wind;

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Sys getSys() {
        return sys;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getDt() {
        return dt;
    }

    public void setCurrentWeatherCoord(CurrentWeatherCoord coord) {
        this.currentWeatherCoord = coord;
    }

    public CurrentWeatherCoord getCurrentWeatherCoord() {
        return currentWeatherCoord;
    }

    public void setWeather(List<WeatherItem> weather) {
        this.weather = weather;
    }

    public List<WeatherItem> getWeather() {
        return weather;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getBase() {
        return base;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Wind getWind() {
        return wind;
    }

    @Override
    public String toString() {
        return
                "CurrentWeather{" +
                        "visibility = '" + visibility + '\'' +
                        ",timezone = '" + timezone + '\'' +
                        ",main = '" + main + '\'' +
                        ",clouds = '" + clouds + '\'' +
                        ",sys = '" + sys + '\'' +
                        ",dt = '" + dt + '\'' +
                        ",coord = '" + currentWeatherCoord + '\'' +
                        ",weather = '" + weather + '\'' +
                        ",name = '" + name + '\'' +
                        ",cod = '" + cod + '\'' +
                        ",id = '" + id + '\'' +
                        ",base = '" + base + '\'' +
                        ",wind = '" + wind + '\'' +
                        "}";
    }

}