package com.raahi.simpleweatherapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.raahi.simpleweatherapp.R;
import com.raahi.simpleweatherapp.api.ApiClient;
import com.raahi.simpleweatherapp.api.ApiInterface;
import com.raahi.simpleweatherapp.common.Const;
import com.raahi.simpleweatherapp.common.Util;
import com.raahi.simpleweatherapp.fragment.ErrorPage;
import com.raahi.simpleweatherapp.fragment.LoadingPage;
import com.raahi.simpleweatherapp.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashActivity extends AppCompatActivity implements LoadingPage.GetWeatherData, ErrorPage.OnRefreshButtonListener {
    private static final String TAG = SplashActivity.class.getSimpleName();
    private LoadingPage loadingPage;
    private ErrorPage errorPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment();
    }

    private void addFragment() {
        if (!Util.isNetworkAvailable(this)) {
            errorPage = ErrorPage.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, errorPage).commit();
        } else {
            loadingPage = LoadingPage.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, loadingPage).commit();
        }
    }

    @Override
    public void getWeatherData() {
        getRefreshedData();
    }

    private void getRefreshedData() {
        if (!Util.isNetworkAvailable(this)) {
            if (errorPage == null)
                errorPage = ErrorPage.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, errorPage).commit();
        } else {
            getData();
        }
    }

    //Get WeatherData
    private void getData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<WeatherResponse> call = apiService.getWeather(Const.API_KEY, "Bengaluru", "7");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(SplashActivity.this, WeatherDetailsActivity.class);
                    intent.putExtra("weather_response", response.body());
                    startActivity(intent);
                    Log.v(TAG + "-Success", new Gson().toJson(response.body()));
                } else {
                    if (errorPage == null)
                        errorPage = ErrorPage.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, errorPage).commit();
                    Log.v(TAG + "-unSuccess", new Gson().toJson(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e(TAG + " error", t.toString());
                if (errorPage == null)
                    errorPage = ErrorPage.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, errorPage).commit();
            }
        });
    }

    @Override
    public void onRefresh() {
        getRefreshedData();
    }
}