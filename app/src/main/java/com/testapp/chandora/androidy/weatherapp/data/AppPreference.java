package com.testapp.chandora.androidy.weatherapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by chandora on 31-May-2019
 */
public class AppPreference {

    private static final String CITY_NAME = "CITY_NAME";


    private static volatile AppPreference INSTANCE;
    private final SharedPreferences prefs;

    public static AppPreference getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppPreference.class) {
                INSTANCE = new AppPreference(context);
            }
        }
        return INSTANCE;
    }

    private AppPreference(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

   public String getCityName(){
        return prefs.getString(CITY_NAME,"");
    }

    public void setCityName(String city){
        prefs.edit().putString(CITY_NAME,city).commit();
    }
}
