package com.raahi.simpleweatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Raahi on 06-07-2018.
 */
public class Forecast implements Serializable {
    @SerializedName("forecastday")
    @Expose
    private List<ForecastDay> forecastday = null;

    public List<ForecastDay> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<ForecastDay> forecastday) {
        this.forecastday = forecastday;
    }
}
