package com.nelson.weatherapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.nelson.weatherapp.networking.WeatherRepository;
import com.nelson.weatherapp.networking.model.ApiResponse;
import com.nelson.weatherapp.networking.model.CurrentObservation;
import com.nelson.weatherapp.networking.model.Forecastday_;
import com.nelson.weatherapp.networking.model.HourlyForecast;

import java.util.List;

/**
 * Created by Nelson on 9/22/2017.
 */

/**
 * Viewmodel saves data on configuration change and avoid unnecessary network calls
 */
public class WeatherViewModel extends ViewModel {
    private WeatherRepository weatherRepository;
    private LiveData<List<HourlyForecast>> hourlyForecastLiveData;
    private LiveData<CurrentObservation> currentObservationLiveData;
    private LiveData<List<Forecastday_>> forecastDayLiveData;

    public WeatherViewModel(){
        weatherRepository = new WeatherRepository();
        forecastDayLiveData = weatherRepository.getForecast();
        hourlyForecastLiveData = weatherRepository.getHourlyForecast();
        currentObservationLiveData = weatherRepository.getCurrentObservation();
    }

    public LiveData<List<Forecastday_>> getForecast(){
        return forecastDayLiveData;
    }

    public LiveData<List<HourlyForecast>> getHourlyForecast(){
        return hourlyForecastLiveData;
    }
    public LiveData<ApiResponse> getWeatherData() {
        return weatherRepository.getWeatherData();
    }
    public LiveData<CurrentObservation> getCurrentObservation() {
        return currentObservationLiveData;
    }
}
