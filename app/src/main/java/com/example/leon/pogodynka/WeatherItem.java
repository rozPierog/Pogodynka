package com.example.leon.pogodynka;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Leon on 10/22/2017.
 */

public class WeatherItem extends AbstractItem<WeatherItem, WeatherItem.ViewHolder> {

    String temp;
    public WeatherItem() {}
    public WeatherItem(String temp) {
        this.temp = temp;
    }

    @Override
    public int getType() {
        return R.id.weather_card;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.cell_weather_item;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);
        viewHolder.temp.setText(temp);
        viewHolder.weatherImage.setImageResource(R.mipmap.ic_launcher_foreground);

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
        @BindView(R.id.textView)
        TextView temp;
        @BindView(R.id.weatherItemImg)
        ImageView weatherImage;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}
