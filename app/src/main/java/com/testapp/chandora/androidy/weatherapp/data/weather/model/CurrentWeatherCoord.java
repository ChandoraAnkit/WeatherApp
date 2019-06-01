package com.testapp.chandora.androidy.weatherapp.data.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chandora on 01-Jun-2019
 */
public class CurrentWeatherCoord {
    @SerializedName("lon")
    private double currentLon;

    @SerializedName("lat")
    private double currentLat;


    public double getCurrentLon() {
        return currentLon;
    }

    public void setCurrentLon(double currentLon) {
        this.currentLon = currentLon;
    }

    public double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(double currentLat) {
        this.currentLat = currentLat;
    }

    @Override
    public String toString(){
        return
                "Coord{" +
                        "lon = '" + currentLon + '\'' +
                        ",lat = '" + currentLat + '\'' +
                        "}";
    }
}
