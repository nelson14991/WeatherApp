
package com.nelson.weatherapp.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features {

    @SerializedName("forecast10day")
    @Expose
    private Integer forecast10day;
    @SerializedName("conditions")
    @Expose
    private Integer conditions;

    @SerializedName("hourly")
    @Expose
    private Integer hourly;

    public Integer getForecast10day() {
        return forecast10day;
    }

    public void setForecast10day(Integer forecast10day) {
        this.forecast10day = forecast10day;
    }

    public Integer getConditions() {
        return conditions;
    }

    public void setConditions(Integer conditions) {
        this.conditions = conditions;
    }

    public Integer getHourly() {
        return hourly;
    }

    public void setHourly(Integer hourly) {
        this.hourly = hourly;
    }
}
