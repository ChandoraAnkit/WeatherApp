package com.testapp.chandora.androidy.weatherapp.data.weather.source.local;

import android.util.Log;

import com.testapp.chandora.androidy.weatherapp.data.weather.model.WeatherDetails;
import com.testapp.chandora.androidy.weatherapp.data.weather.source.WeatherDataSource;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chandora on 31-May-2019
 */
public class WeatherLocalDataSource implements WeatherDataSource {

    private static volatile WeatherLocalDataSource INSTANCE = null;

    private WeatherDetailsDatabase mWeatherDetailsDatabase;
    private WeatherDetailsDao mWeatherDetailsDao;


    // Prevent direct instantiation.
    private WeatherLocalDataSource(WeatherDetailsDatabase weatherDetailsDatabase) {
        mWeatherDetailsDatabase = weatherDetailsDatabase;

        mWeatherDetailsDao = mWeatherDetailsDatabase.daoAccess();
    }


    public static WeatherLocalDataSource getInstance(WeatherDetailsDatabase weatherDetailsDatabase) {
        if (INSTANCE == null) {
            INSTANCE = new WeatherLocalDataSource(weatherDetailsDatabase);
        }
        return INSTANCE;
    }


    @Override
    public void getWeatherDetailsFromServer(String query, OnWeatherDetailsLoadedCallback callback) {
        //Not applicable here
    }

    @Override
    public void getWeatherDetailsFromLocal(OnWeatherDetailsLoadedCallback weatherCallback) {

        mWeatherDetailsDao.fetchedWeatherDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<WeatherDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(WeatherDetails weatherDetails) {
                        weatherCallback.onSuccessDataLoaded(weatherDetails);
                    }

                    @Override
                    public void onError(Throwable e) {
                        weatherCallback.onError();
                    }
                });


    }

    @Override
    public void savedWeatherDetailsLocally(WeatherDetails weatherDetails) {

        Completable.fromAction(() -> {
            mWeatherDetailsDao.insertWeatherDetails(weatherDetails);
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();

    }
}
