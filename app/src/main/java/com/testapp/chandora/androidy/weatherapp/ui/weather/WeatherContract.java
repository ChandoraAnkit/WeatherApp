package com.testapp.chandora.androidy.weatherapp.ui.weather;

import com.testapp.chandora.androidy.weatherapp.data.weather.model.WeatherDetails;
import com.testapp.chandora.androidy.weatherapp.base.BasePresenter;
import com.testapp.chandora.androidy.weatherapp.base.BaseView;

/**
 * Created by chandora on 31-May-2019
 */
public interface WeatherContract {

    interface View extends BaseView<Presenter>{

        void showDetails(WeatherDetails weatherDetails, boolean isFromServer);

        void showError();

        void showProgress();

        void hideProgress();

        void noDataFound();
    }

    interface Presenter extends BasePresenter{

        void getWeatherDetails(String query);

        void fetchWeatherDetailsLocally();


    }

}
