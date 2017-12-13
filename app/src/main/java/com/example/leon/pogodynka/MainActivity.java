package com.example.leon.pogodynka;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.leon.pogodynka.database.Cities;
import com.example.leon.pogodynka.database.City;
import com.example.leon.pogodynka.database.PogodynkaDatabase;
import com.example.leon.pogodynka.feature.forecast.WeatherFragment;
import com.example.leon.pogodynka.feature.search.SearchFragment;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends FragmentActivity {

    private TextView mTextMessage;


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new WeatherFragment()).commit();
        ButterKnife.bind(this);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_weather:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new WeatherFragment()).commit();
                        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        mToolbar.setTitle(getResources().getString(R.string.weather_tab));
                        break;
                    case R.id.tab_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new SearchFragment()).commit();
                        mToolbar.setBackgroundColor(getResources().getColor(R.color.accent));
                        mToolbar.setTitle(getResources().getString(R.string.search_tab));
                        break;

                }
            }
        });

//        PogodynkaDatabase database = PogodynkaApp.get().getDB();
//        InputStream inputStream = this.getResources().openRawResource(R.raw.city_list);
//
//        try {
//            final JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
//            Observable.just(database)
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(new Observer<PogodynkaDatabase>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(PogodynkaDatabase pogodynkaDatabase) {
//                            pogodynkaDatabase.cityDao().nukeTable();
//                            Cities cities = new Gson().fromJson(reader, Cities.class);
//                            pogodynkaDatabase.cityDao().insertAll(cities.getCities());
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }





    }

    private String readJsonFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte bufferByte[] = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(bufferByte)) != -1) {
                outputStream.write(bufferByte, 0, length);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();


    }

}
