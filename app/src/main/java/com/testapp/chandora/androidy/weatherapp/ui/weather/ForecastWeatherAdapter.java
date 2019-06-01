package com.testapp.chandora.androidy.weatherapp.ui.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.testapp.chandora.androidy.weatherapp.R;
import com.testapp.chandora.androidy.weatherapp.data.weather.model.Forecast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chandora on 31-May-2019
 */
public class ForecastWeatherAdapter extends RecyclerView.Adapter<ForecastWeatherAdapter.ForecastViewHolder> {

    private ArrayList<Forecast> mForecasts;

    public ForecastWeatherAdapter() {
        mForecasts = new ArrayList<>();
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast_layout, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        holder.bind(mForecasts.get(position));
    }


    public void setData(ArrayList<Forecast> forecastList) {
        if (forecastList != null && forecastList.size() > 0) {
            mForecasts.clear();
            mForecasts.addAll(forecastList);
            notifyDataSetChanged();
        }

    }


    @Override
    public int getItemCount() {
        return mForecasts.size();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.forecast_day_tv)
        TextView mDayTv;

        @BindView(R.id.forecast_date_tv)
        TextView mDateTv;

        @BindView(R.id.forecast_iv)
        ImageView mWeatherIv;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Forecast weather) {

            mDayTv.setText(weather.getDay());
            mDateTv.setText(weather.getDate());

            StringBuilder imageUrl = new StringBuilder();
            imageUrl.append("http://openweathermap.org/img/w/")
                    .append(weather.getIcon())
                    .append(".png");

            Glide.with(itemView.getContext()).load(imageUrl.toString()).into(mWeatherIv);
        }
    }
}
