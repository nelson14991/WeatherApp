package com.nelson.weatherapp.networking;

import com.nelson.weatherapp.networking.model.ApiResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nelson on 9/17/2017.
 */

public interface ApiService {
    @GET("api/5323d9e146f3691f/hourly/forecast10day/conditions/q/autoip.json")
    Call<ApiResponse> getWeatherData();
}
