package com.nelson.weatherapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nelson.weatherapp.networking.WeatherRepository;
import com.nelson.weatherapp.networking.model.ApiResponse;
import com.nelson.weatherapp.networking.model.CurrentObservation;
import com.nelson.weatherapp.networking.model.Forecastday_;
import com.nelson.weatherapp.networking.model.HourlyForecast;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment{

    private ForecastListAdapter forecastListAdapter;
    private HourlyListAdapter hourlyListAdapter;
    RecyclerView dailyForecastList;
    RecyclerView hourlyForecastList;
    CardView currentCondition;
    private WeatherViewModel weatherViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        if(Util.isConnectedToInternet(getActivity())){
            fetchData();
        }
        else {
            Toast.makeText(getActivity().getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
        }
    }

    /**
     *  Fetch data from ViewModel
     */
    private void fetchData() {
        weatherViewModel.getForecast().observe(this, new Observer<List<Forecastday_>>() {
            @Override
            public void onChanged(@Nullable List<Forecastday_> forecastday_s) {
                forecastListAdapter = new ForecastListAdapter(forecastday_s,getActivity());
                dailyForecastList.setAdapter(forecastListAdapter);
            }
        });

        weatherViewModel.getHourlyForecast().observe(this, new Observer<List<HourlyForecast>>() {
            @Override
            public void onChanged(@Nullable List<HourlyForecast> hourlyForecasts) {
                if(hourlyForecasts!=null){
                    hourlyListAdapter = new HourlyListAdapter(hourlyForecasts,getActivity());
                    hourlyForecastList.setAdapter(hourlyListAdapter);
                }

            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        dailyForecastList = view.findViewById(R.id.forecastList);
        assert dailyForecastList !=null;
        setupDailyForecastRecyclerView((RecyclerView) dailyForecastList);

        hourlyForecastList = view.findViewById(R.id.hourlyForecastList);
        assert hourlyForecastList!=null;
        setupHourlyForecastRecyclerView(hourlyForecastList);

        currentCondition = view.findViewById(R.id.current_temp);
        setUpCurrentCondition(currentCondition);


    }

    private void setupHourlyForecastRecyclerView(RecyclerView hourlyForecastList) {
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        hourlyForecastList.addItemDecoration(itemDecoration);
        hourlyForecastList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
    }


    private void setUpCurrentCondition(CardView currentCondition) {
        weatherViewModel.getCurrentObservation().observe(this, new Observer<CurrentObservation>() {
            @Override
            public void onChanged(@Nullable CurrentObservation currentObservation) {
                TextView currentTempText = currentCondition.findViewById(R.id.currentTempText);
                TextView currentCity = currentCondition.findViewById(R.id.currentCity);
                ImageView currentWeatherIcon = currentCondition.findViewById(R.id.currentWeatherIcon);
                TextView currentWeatherType = currentCondition.findViewById(R.id.currentWeatherType);

                currentCity.setText(currentObservation.getDisplayLocation().getFull());
                currentTempText.setText(String.valueOf(currentObservation.getTempF().intValue()) + (char) 0x00b0);
                Glide.with(getActivity()).load(currentObservation.getIconUrl()).into(currentWeatherIcon);
                currentWeatherType.setText(currentObservation.getWeather());
            }
        });

    }

    private void setupDailyForecastRecyclerView(@NonNull RecyclerView recyclerView) {
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
    }

}
