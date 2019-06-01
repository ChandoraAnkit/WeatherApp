package com.testapp.chandora.androidy.weatherapp.ui.weather;

import com.testapp.chandora.androidy.weatherapp.data.weather.model.WeatherDetails;
import com.testapp.chandora.androidy.weatherapp.data.weather.source.WeatherDataRepository;
import com.testapp.chandora.androidy.weatherapp.data.weather.source.WeatherDataSource;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by chandora on 31-May-2019
 */
public class WeatherPresenter implements WeatherContract.Presenter {

    private final WeatherContract.View mView;
    private final WeatherDataRepository mWeatherRepository;

    private CompositeDisposable disposable ;


    public WeatherPresenter(WeatherContract.View view,
                            WeatherDataRepository weatherDataRepository){

        mView = view;
        mWeatherRepository = weatherDataRepository;

        disposable = new CompositeDisposable();
        mView.setPresenter(this);
    }


    @Override
    public void getWeatherDetails(String query) {

        mWeatherRepository.getWeatherDetailsFromServer(query, new WeatherDataSource.OnWeatherDetailsLoadedCallback(){

            @Override
            public void onSuccessDataLoaded(WeatherDetails weatherDetails) {

                mWeatherRepository.savedWeatherDetailsLocally(weatherDetails);
                mView.showDetails(weatherDetails,true);
            }

            @Override
            public void onError() {
                mView.showError();
            }
        });
    }

    @Override
    public void fetchWeatherDetailsLocally() {

        mWeatherRepository.getWeatherDetailsFromLocal(new WeatherDataSource.OnWeatherDetailsLoadedCallback() {
            @Override
            public void onSuccessDataLoaded(WeatherDetails weatherDetails) {
                mView.showDetails(weatherDetails,false);
            }

            @Override
            public void onError() {
                mView.noDataFound();
            }
        });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }
}
