package com.example.leon.pogodynka.api;

import com.example.leon.pogodynka.api.models.current.ApiCurrentData;
import com.example.leon.pogodynka.api.models.five_day.ApiDaily;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Leon on 12-Dec-17.
 */

public interface PogodynkaService {

    @GET("weather?APPID=&units=metric&")
    Observable<ApiCurrentData> getCurrentData(@Query("q") String city);

    @GET("forecast?APPID=&units=metric&")
    Observable<ApiDaily> getForecast(@Query("q") String city);
}
