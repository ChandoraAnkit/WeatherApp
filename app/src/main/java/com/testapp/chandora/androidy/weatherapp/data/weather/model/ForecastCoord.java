package com.testapp.chandora.androidy.weatherapp.data.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chandora on 01-Jun-2019
 */
public class ForecastCoord {
    @SerializedName("lon")
    private double forecastLon;

    @SerializedName("lat")
    private double forecastLat;

    public double getForecastLon() {
        return forecastLon;
    }

    public void setForecastLon(double forecastLon) {
        this.forecastLon = forecastLon;
    }

    public double getForecastLat() {
        return forecastLat;
    }

    public void setForecastLat(double forecastLat) {
        this.forecastLat = forecastLat;
    }

    @Override
    public String toString(){
        return
                "Coord{" +
                        "lon = '" + forecastLon + '\'' +
                        ",lat = '" + forecastLat + '\'' +
                        "}";
    }
}
