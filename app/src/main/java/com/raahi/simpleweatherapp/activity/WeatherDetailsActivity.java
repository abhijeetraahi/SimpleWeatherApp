package com.raahi.simpleweatherapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.raahi.simpleweatherapp.R;
import com.raahi.simpleweatherapp.adapter.WeatherItemAdapter;
import com.raahi.simpleweatherapp.model.WeatherResponse;

/**
 * Created by Raahi on 09-07-2018.
 */

public class WeatherDetailsActivity extends AppCompatActivity {
    private static final String RESPONSE = "weather_response";
    private WeatherResponse weatherResponse;
    private WeatherItemAdapter weatherItemAdapter;
    private RecyclerView recyclerView;
    private TextView minTemp, cityName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_details_activity);

        initAppBarLayout();
        init();

    }

    private void initAppBarLayout() {
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("Weather App");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle("");
                    isShow = false;
                }
            }
        });
    }

    private void init() {
        weatherResponse = (WeatherResponse) getIntent().getSerializableExtra("weather_response");
        minTemp = findViewById(R.id.min_temp);
        cityName = findViewById(R.id.city_name);

        minTemp.setText(String.format("%s°", weatherResponse.getForecast().getForecastday().get(0).getDay().getMintempC()));
        cityName.setText(weatherResponse.getLocation().getName());

        recyclerView = findViewById(R.id.rv_item);
        weatherItemAdapter = new WeatherItemAdapter(weatherResponse.getForecast());
        if (weatherResponse != null) {
            recyclerView.setAdapter(weatherItemAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }
    }
}
