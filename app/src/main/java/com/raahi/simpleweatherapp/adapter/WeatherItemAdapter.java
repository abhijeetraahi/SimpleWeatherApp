package com.raahi.simpleweatherapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raahi.simpleweatherapp.R;
import com.raahi.simpleweatherapp.common.Util;
import com.raahi.simpleweatherapp.model.Forecast;
import com.raahi.simpleweatherapp.model.ForecastDay;
import com.squareup.picasso.Picasso;

/**
 * Created by Raahi on 09-07-2018.
 */

public class WeatherItemAdapter extends RecyclerView.Adapter<WeatherItemAdapter.WeatherItemHolder> {
    private Forecast forecast;

    public WeatherItemAdapter(Forecast forecast) {
        this.forecast = forecast;
    }

    @Override
    public WeatherItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.weather_item, parent, false);
        // Return a new holder instance
        return new WeatherItemHolder(contactView);
    }

    @Override
    public void onBindViewHolder(WeatherItemHolder holder, int position) {
        //  holder.bind(forecast.getForecastDay().getHour().get(position));
        holder.bind(forecast.getForecastday().get(position));
    }

    @Override
    public int getItemCount() {
        return forecast.getForecastday().size();
    }

    public class WeatherItemHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView date;
        TextView forecast;
        TextView tempC;

        public WeatherItemHolder(final View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_img);
            date = itemView.findViewById(R.id.tv_date);
            forecast = itemView.findViewById(R.id.tv_forecast);
            tempC = itemView.findViewById(R.id.tv_temp);
        }

        public void bind(ForecastDay forecastDay) {
            date.setText(Util.ConvertDateToDay(forecastDay.getDate()));
            Picasso.get().load("http://".concat(forecastDay.getDay().getCondition().getIcon().substring(2, forecastDay.getDay().getCondition().getIcon().length()))).into(img);
            forecast.setText(String.format(forecastDay.getDay().getCondition().getText()));
            tempC.setText(String.format("%s°", forecastDay.getDay().getMintempC()) + "/" + String.format("%s°", forecastDay.getDay().getMaxtempC()));
        }
    }
}
