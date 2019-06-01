package com.testapp.chandora.androidy.weatherapp.data.weather.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.testapp.chandora.androidy.weatherapp.data.weather.model.WeatherDetails;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;

/**
 * Created by chandora on 01-Jun-2019
 */

@Dao
public interface WeatherDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeatherDetails(WeatherDetails weatherDetails);

    @Query("SELECT * FROM WeatherDetails LIMIT 1")
    Single<WeatherDetails> fetchedWeatherDetails();

}
