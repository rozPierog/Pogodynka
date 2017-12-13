package com.example.leon.pogodynka.feature.forecast;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.pogodynka.PogodynkaApp;
import com.example.leon.pogodynka.R;
import com.example.leon.pogodynka.api.models.current.ApiCurrentData;
import com.example.leon.pogodynka.api.models.five_day.ApiDaily;
import com.example.leon.pogodynka.api.models.five_day.ListItem;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Leon on 10/22/2017.
 */

public class WeatherItem extends AbstractItem<WeatherItem, WeatherItem.ViewHolder> {

    String temp;
    ApiCurrentData apiCurrentData;
    ListItem apiDaily;

    public WeatherItem() {
    }

    public WeatherItem(String temp) {
        this.temp = temp;
    }

    public WeatherItem(ApiCurrentData data) {
        apiCurrentData = data;
    }

    public WeatherItem(ListItem daily) {
        apiDaily = daily;
    }

    @Override
    public int getType() {
        return R.id.weather_card;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.item_weather;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);

        if(apiCurrentData != null && apiDaily == null) {
            viewHolder.date.setText(getDate(apiCurrentData.getDt()));
            viewHolder.temp.setText(String.valueOf(apiCurrentData.getMain().getTemp()) + "°C");
            viewHolder.discription.setText(apiCurrentData.getWeather().get(0).getDescription());
            Picasso.with(PogodynkaApp.get())
                    .load("http://openweathermap.org/img/w/" + apiCurrentData.getWeather().get(0).getIcon() + ".png")
                    .into(viewHolder.weatherImage);
        }
        if(apiDaily != null && apiCurrentData == null) {
            viewHolder.date.setText(getDate(apiDaily.getDt()));
            viewHolder.temp.setText(String.valueOf(apiDaily.getMain().getTemp()) + "°C");
            viewHolder.discription.setText(apiDaily.getWeather().get(0).getDescription());
            Picasso.with(PogodynkaApp.get())
                    .load("http://openweathermap.org/img/w/" + apiDaily.getWeather().get(0).getIcon() + ".png")
                    .into(viewHolder.weatherImage);
        }
    }

    private String getDate(long timeStamp){

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm MM/dd/yyyy");
            Date netDate = (new Date(timeStamp*1000L));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }


    //reset the view here (this is an optional method, but recommended)
    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.weather_temp)
        TextView temp;
        @BindView(R.id.weather_discription)
        TextView discription;
        @BindView(R.id.weather_date)
        TextView date;
        @BindView(R.id.weatherItemImg)
        ImageView weatherImage;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}
