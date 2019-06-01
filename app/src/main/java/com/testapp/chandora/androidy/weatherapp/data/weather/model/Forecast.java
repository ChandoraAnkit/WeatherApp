package com.testapp.chandora.androidy.weatherapp.data.weather.model;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chandora on 31-May-2019
 */
public class Forecast {

    private String day;

    private String icon;

    private String date;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        DateFormat fromSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date fromDate = fromSdf.parse(date);
            this.date =  new SimpleDateFormat("MM/dd hh:mm a").format(fromDate);


            this.day = new SimpleDateFormat("E").format(fromDate);

        } catch (ParseException e) {
            e.printStackTrace();
            this.date = " - ";
        }


    }
}
