package com.testapp.chandora.androidy.weatherapp.data.weather.source.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.testapp.chandora.androidy.weatherapp.data.weather.model.WeatherDetails;

import static okhttp3.internal.Internal.instance;

/**
 * Created by chandora on 01-Jun-2019
 */

@Database(entities = {WeatherDetails.class},version = 1)
public abstract class WeatherDetailsDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "Weather.db";

    private static WeatherDetailsDatabase INSTANCE;

    public abstract WeatherDetailsDao daoAccess();

    private static final Object sLock = new Object();


    public static WeatherDetailsDatabase getInstance(Context context) {

        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        WeatherDetailsDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return INSTANCE;

        }
    }
}
