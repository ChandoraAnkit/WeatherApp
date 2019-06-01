package com.testapp.chandora.androidy.weatherapp.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.testapp.chandora.androidy.weatherapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CityFragment extends Fragment {

    private OnCityEnteredClickListener mListener;

    @BindView(R.id.city_et)
    EditText mCityEditText;


    public CityFragment() {
        // Required empty public constructor
    }

    public static CityFragment newInstance() {
        CityFragment fragment = new CityFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_city, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCityEnteredClickListener) {
            mListener = (OnCityEnteredClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCityEnteredListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.city_button_iv)
    public void onCityEnteredButtonClicked() {

        String city = mCityEditText.getText().toString();

        if (!city.isEmpty())
            mListener.onCityEntered(city);
        else
            mCityEditText.setError("City can't be empty!");
    }


    public interface OnCityEnteredClickListener {

        void onCityEntered(String cityName);
    }
}
