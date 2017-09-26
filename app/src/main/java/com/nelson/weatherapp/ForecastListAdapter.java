package com.nelson.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nelson.weatherapp.networking.model.Forecastday_;

import java.util.List;

/**
 * Created by Nelson on 9/18/2017.
 */

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ViewHolder> {

    private List<Forecastday_> forecastday_list;
    private Context mContext;
    public ForecastListAdapter(List<Forecastday_> forecastday_list, Context applicationContext){
        this.forecastday_list = forecastday_list;
        mContext = applicationContext;
    }

    @Override
    public ForecastListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_forecast_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastListAdapter.ViewHolder holder, int position) {
        holder.day.setText(forecastday_list.get(position).getDate().getWeekdayShort());
        Glide.with(mContext).load(forecastday_list.get(position).getIconUrl()).into(holder.imageView);
        holder.highTempTextView.setText(forecastday_list.get(position).getHigh().getFahrenheit() + (char) 0x00b0);
        holder.lowTempTextView.setText(forecastday_list.get(position).getLow().getFahrenheit() + (char) 0x00b0);
    }

    @Override
    public int getItemCount() {
        return forecastday_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView day;
        public ImageView imageView;
        public TextView highTempTextView;
        public TextView lowTempTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            day = itemView.findViewById(R.id.day);
            imageView = itemView.findViewById(R.id.imageTemp);
            highTempTextView = itemView.findViewById(R.id.high);
            lowTempTextView = itemView.findViewById(R.id.low);
        }
    }
}
