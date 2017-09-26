package com.nelson.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nelson.weatherapp.networking.model.HourlyForecast;

import java.util.List;

/**
 * Created by Nelson on 9/18/2017.
 */

public class HourlyListAdapter extends RecyclerView.Adapter<HourlyListAdapter.ViewHolder> {

    private List<HourlyForecast> hourlyForecast_list;
    private Context mContext;
    public HourlyListAdapter(List<HourlyForecast> hourlyForecast_list, Context applicationContext){
        this.hourlyForecast_list = hourlyForecast_list;
        mContext = applicationContext;
    }

    @Override
    public HourlyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_forecast_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HourlyListAdapter.ViewHolder holder, int position) {
        String hourlyText = hourlyForecast_list.get(position).getFCTTIME().getCivil();
        holder.hourlyTime.setText(hourlyText);
        Glide.with(mContext).load(hourlyForecast_list.get(position).getIconUrl()).into(holder.imageView);
        holder.hourlyTemp.setText(hourlyForecast_list.get(position).getTemp().getEnglish()+ (char) 0x00b0);

    }

    @Override
    public int getItemCount() {
        return hourlyForecast_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView hourlyTime;
        public ImageView imageView;
        public TextView hourlyTemp;

        public ViewHolder(View itemView) {
            super(itemView);

            hourlyTime = itemView.findViewById(R.id.hourly_time);
            imageView = itemView.findViewById(R.id.hourly_temp_img);
            hourlyTemp = itemView.findViewById(R.id.hourly_temp);
        }
    }
}
