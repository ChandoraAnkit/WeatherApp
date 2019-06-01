package com.testapp.chandora.androidy.weatherapp.ui.weather;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.testapp.chandora.androidy.weatherapp.R;
import com.testapp.chandora.androidy.weatherapp.data.weather.model.Forecast;
import com.testapp.chandora.androidy.weatherapp.data.weather.model.ForecastWeather;
import com.testapp.chandora.androidy.weatherapp.data.weather.model.ListItem;
import com.testapp.chandora.androidy.weatherapp.data.weather.model.WeatherDetails;
import com.testapp.chandora.androidy.weatherapp.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.testapp.chandora.androidy.weatherapp.utils.NetworkUtils.isNetworkAvailable;


public class WeatherFragment extends Fragment implements WeatherContract.View {

    private static final String CITY_NAME = "city";

    private String cityName;

    private WeatherContract.Presenter mPresenter;

    private ForecastWeatherAdapter forecastWeatherAdapter;

    private ProgressDialog progressDialog;

    private OnCityButtonClickListener mClickListener;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.city_name_tv)
    TextView mCityNameTv;

    @BindView(R.id.temp_tv)
    TextView mTempTv;

    @BindView(R.id.latitude_tv)
    TextView mLatitudeTv;

    @BindView(R.id.longitude_tv)
    TextView mLongitudeTv;

    @BindView(R.id.description_tv)
    TextView mDescriptionTv;

    @BindView(R.id.weather_iv)
    ImageView mWeatherIv;

    @BindView(R.id.forecast_rv)
    RecyclerView mForecastRecyclerView;


    public WeatherFragment() {
        // Required empty public constructor
    }


    public static WeatherFragment newInstance(String cityName) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(CITY_NAME, cityName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCityButtonClickListener) {
            mClickListener = (OnCityButtonClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCityButtonClickedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cityName = getArguments().getString(CITY_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Data is loading...");
        showProgress();

        forecastWeatherAdapter = new ForecastWeatherAdapter();

        mForecastRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mForecastRecyclerView.setAdapter(forecastWeatherAdapter);

        mPresenter.fetchWeatherDetailsLocally();

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.getWeatherDetails(cityName);

        });
    }


    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showDetails(WeatherDetails weather, boolean isFromServer) {

        hideProgress();

        mCityNameTv.setText(weather.getCurrentWeather().getName());
        mTempTv.setText(new StringBuilder().append(weather.getCurrentWeather().getMain().getTemp()).append(" K").toString());
        mLatitudeTv.setText(new StringBuilder().append(weather.getCurrentWeather().getCurrentWeatherCoord().getCurrentLat()).append(" Lat").toString());
        mLongitudeTv.setText(new StringBuilder().append(weather.getCurrentWeather().getCurrentWeatherCoord().getCurrentLon()).append(" Long").toString());
        mDescriptionTv.setText(String.valueOf(weather.getCurrentWeather().getWeather().get(0).getDescription()));

        String imageIcon = weather.getCurrentWeather().getWeather().get(0).getIcon();

        StringBuilder imageUrl = new StringBuilder();
        imageUrl.append("http://openweathermap.org/img/w/")
                .append(imageIcon)
                .append(".png");

        Glide.with(getContext()).load(imageUrl.toString()).into(mWeatherIv);

        ForecastWeather forecastWeather = weather.getForecastWeather();

        List<ListItem> forecastList = forecastWeather.getList();

        ArrayList<Forecast> resultForecastList = new ArrayList<>();

        for (ListItem item : forecastList) {

            Forecast forecast = new Forecast();
            forecast.setDate(item.getDtTxt());
            forecast.setIcon(item.getWeather().get(0).getIcon());

            resultForecastList.add(forecast);
        }

        forecastWeatherAdapter.setData(resultForecastList);

        if (!isFromServer && isNetworkAvailable(getContext()))
            mPresenter.getWeatherDetails(cityName);
        else if(!isFromServer && !isNetworkAvailable(getContext())){
            Toast.makeText(getContext(), "You are not connected to internet!", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.city_btn_iv)
    public void enterCityButtonClicked() {
        mClickListener.onCityButtonClicked();
    }

    @Override
    public void showError() {
        hideProgress();
        Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);

        progressDialog.cancel();
    }

    @Override
    public void noDataFound() {
        mPresenter.getWeatherDetails(cityName);
    }

    public interface OnCityButtonClickListener {

        void onCityButtonClicked();
    }
}
