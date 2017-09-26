package com.nelson.weatherapp.networking;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.nelson.weatherapp.networking.model.ApiResponse;
import com.nelson.weatherapp.networking.model.CurrentObservation;
import com.nelson.weatherapp.networking.model.Forecastday_;
import com.nelson.weatherapp.networking.model.HourlyForecast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nelson on 9/17/2017.
 */

/**
 * Repository to manage networking
 * Converts Call<T> from Retrofit to LiveData<T> that can be observed by fragment lifecycle
 */
public class WeatherRepository {
    private ApiService apiService;

    public WeatherRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.wunderground.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public LiveData<ApiResponse> getWeatherData(){
        final MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        apiService.getWeatherData().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
            }
        });
        return data;
    }

    public LiveData<List<Forecastday_>> getForecast(){
        final MutableLiveData<List<Forecastday_>> data = new MutableLiveData<>();
        apiService.getWeatherData().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                data.setValue(response.body().getForecast().getSimpleforecast().getForecastday());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
            }
        });
        return data;
    }


    public LiveData<CurrentObservation> getCurrentObservation(){
    final MutableLiveData<CurrentObservation> data = new MutableLiveData<>();
    apiService.getWeatherData().enqueue(new Callback<ApiResponse>() {
        @Override
        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            data.setValue(response.body().getCurrentObservation());
        }

        @Override
        public void onFailure(Call<ApiResponse> call, Throwable t) {
        }
    });
    return data;
}

    public LiveData<List<HourlyForecast>> getHourlyForecast(){
        final MutableLiveData<List<HourlyForecast>> data = new MutableLiveData<>();
        apiService.getWeatherData().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                data.setValue(response.body().getHourlyForecast());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
        return data;
    }

}
