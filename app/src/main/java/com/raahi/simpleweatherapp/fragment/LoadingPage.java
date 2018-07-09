package com.raahi.simpleweatherapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raahi.simpleweatherapp.R;

/**
 * Created by Raahi on 09-07-2018.
 */

public class LoadingPage extends Fragment {

    private GetWeatherData mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loading_page, container, false);
    }

    public static LoadingPage newInstance() {
        LoadingPage fragment = new LoadingPage();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mListener.getWeatherData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GetWeatherData) {
            mListener = (GetWeatherData) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement GetWeatherData");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface GetWeatherData{
        void getWeatherData();
    }
}
