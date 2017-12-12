package com.example.leon.pogodynka.api;

import android.support.annotation.Nullable;

import com.example.leon.pogodynka.api.models.current.ApiCurrentData;
import com.example.leon.pogodynka.api.models.five_day.ApiDaily;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leon on 12-Dec-17.
 */

public class ApiClient {

    private static final String KEY_API = "53556570e5cfa7aee7ff72a71bf06901";
    private static final String KEY_KOSZALIN = "276656";

    private static ApiClient instance;
    private static PogodynkaService pogodynkaService;

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    private Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    public ApiClient() {
        Retrofit retrofit = builder.build();
        pogodynkaService = retrofit.create(PogodynkaService.class);
    }

    public void getCurrent(String city, ServerResponseListener<ApiCurrentData> callback) {
        makeCall(pogodynkaService.getCurrentData(city), callback);
    }

    public void getForecast(String city, ServerResponseListener<ApiDaily> callback) {
        makeCall(pogodynkaService.getForecast(city), callback);
    }

    private <T> void makeCall(Observable<T> observable, @Nullable final ServerResponseListener<T> callback) {
        observable
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(T responseObject) {
                        if (callback != null)
                            callback.onSuccess(responseObject);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (callback != null)
                            castErrorToHTTP(throwable, callback);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void castErrorToHTTP(final Throwable e, final ServerResponseListener<?> listener) {
        if (e instanceof HttpException) {
            String body = "";
            try {
                e.printStackTrace();
                body = ((HttpException) e).response().errorBody().string();
                Gson gson = new Gson();
                WSError error = gson.fromJson(body, WSError.class);
                listener.onError(error);
            } catch (Exception e1) {
                e1.printStackTrace();
                listener.onError(WSError.getServerConnectionError(body));
            }
        }
        e.printStackTrace();
    }

}
