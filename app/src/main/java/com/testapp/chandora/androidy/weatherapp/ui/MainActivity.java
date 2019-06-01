package com.testapp.chandora.androidy.weatherapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.testapp.chandora.androidy.weatherapp.R;
import com.testapp.chandora.androidy.weatherapp.data.AppPreference;
import com.testapp.chandora.androidy.weatherapp.data.weather.source.WeatherDataRepository;
import com.testapp.chandora.androidy.weatherapp.data.weather.source.local.WeatherDetailsDatabase;
import com.testapp.chandora.androidy.weatherapp.data.weather.source.local.WeatherLocalDataSource;
import com.testapp.chandora.androidy.weatherapp.data.weather.source.remote.WeatherRemoteDataSource;
import com.testapp.chandora.androidy.weatherapp.ui.weather.WeatherFragment;
import com.testapp.chandora.androidy.weatherapp.ui.weather.WeatherPresenter;
import com.testapp.chandora.androidy.weatherapp.utils.NetworkUtils;

import static com.testapp.chandora.androidy.weatherapp.utils.NetworkUtils.isNetworkAvailable;

public class MainActivity extends AppCompatActivity implements
        CityFragment.OnCityEnteredClickListener,
        WeatherFragment.OnCityButtonClickListener {

    private AppPreference mAppPreference = null;

    private WeatherPresenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppPreference = AppPreference.getInstance(this);
        String cityName = mAppPreference.getCityName();

        if (cityName != null && !cityName.isEmpty())
            addWeatherFragment(cityName);
        else
            addCityFragment(true);


        //For loading data after ever 5 minutes.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (presenter != null)
                    if (cityName != null && !cityName.isEmpty())
                        if (isNetworkAvailable(MainActivity.this))
                        presenter.getWeatherDetails(cityName);

                handler.postDelayed(this,5 * 60000);
            }
        },5 * 60000);

    }


    @Override
    public void onCityEntered(String cityName) {

        mAppPreference.setCityName(cityName);
        addWeatherFragment(cityName);
    }

    private void addCityFragment(boolean firstTime) {

        CityFragment cityFragment = CityFragment.newInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!firstTime) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            transaction.addToBackStack(null);
        } else
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        transaction.replace(R.id.container, cityFragment)
                .commit();

    }

    private void addWeatherFragment(String cityName) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        WeatherFragment weatherFragment = WeatherFragment.newInstance(cityName);

        presenter = new WeatherPresenter(weatherFragment, WeatherDataRepository.getInstance(
                WeatherRemoteDataSource.getInstance(),
                WeatherLocalDataSource.getInstance(WeatherDetailsDatabase.getInstance(this))));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, weatherFragment)
                .commit();
    }

    @Override
    public void onCityButtonClicked() {
        addCityFragment(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
