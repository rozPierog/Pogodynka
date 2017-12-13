package com.example.leon.pogodynka.feature.forecast;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leon.pogodynka.R;
import com.example.leon.pogodynka.api.ApiClient;
import com.example.leon.pogodynka.api.ServerResponseListener;
import com.example.leon.pogodynka.api.WSError;
import com.example.leon.pogodynka.api.models.current.ApiCurrentData;
import com.example.leon.pogodynka.api.models.five_day.ApiDaily;
import com.example.leon.pogodynka.api.models.five_day.ListItem;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WeatherFragment extends Fragment {
    private static final String KEY_TOWN_NAME = "TOWN_NAME";

    @BindView(R.id.weatherRecyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.fragment_weather_city)
    TextView city;


    ItemAdapter<WeatherItem> weatherItemAdapter;

    FastAdapter fastAdapter;
    public WeatherFragment() {
        // Required empty public constructor
    }


    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        weatherItemAdapter = new ItemAdapter<>();
        fastAdapter = FastAdapter.with(weatherItemAdapter);
        recyclerView.setAdapter(fastAdapter);
        fastAdapter.withSelectable(true);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String cityFromSharedPref = sharedPref.getString(KEY_TOWN_NAME, "koszalin");
        ApiClient.getInstance().getCurrent(cityFromSharedPref, new ServerResponseListener<ApiCurrentData>() {
            @Override
            public void onSuccess(ApiCurrentData response) {
                showCurrent(response);
            }

            @Override
            public void onError(WSError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @OnClick(R.id.fab_days)
    void onFabDaysClick() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String cityFromSharedPref = sharedPref.getString(KEY_TOWN_NAME, "koszalin");
        ApiClient.getInstance().getForecast(cityFromSharedPref, new ServerResponseListener<ApiDaily>() {
            @Override
            public void onSuccess(ApiDaily response) {
                showForecast(response);
            }

            @Override
            public void onError(WSError error) {

            }
        });
    }

    @OnClick(R.id.fab_current)
    void onFabCurrentClick() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String cityFromSharedPref = sharedPref.getString(KEY_TOWN_NAME, "koszalin");
        ApiClient.getInstance().getCurrent(cityFromSharedPref, new ServerResponseListener<ApiCurrentData>() {
            @Override
            public void onSuccess(ApiCurrentData response) {
                showCurrent(response);
            }

            @Override
            public void onError(WSError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showCurrent(ApiCurrentData data) {
        weatherItemAdapter.clear();
        city.setText(data.getName());
        weatherItemAdapter.add(new WeatherItem(data));
    }

    private void showForecast(ApiDaily daily) {
        weatherItemAdapter.clear();
        for (ListItem listItem :
                daily.getList()) {
            weatherItemAdapter.add(new WeatherItem(listItem));
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
