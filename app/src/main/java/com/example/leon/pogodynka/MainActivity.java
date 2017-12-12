package com.example.leon.pogodynka;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.leon.pogodynka.feature.forecast.WeatherFragment;
import com.example.leon.pogodynka.feature.search.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    }

}
