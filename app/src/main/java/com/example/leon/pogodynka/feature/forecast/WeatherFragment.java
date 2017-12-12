package com.example.leon.pogodynka.feature.forecast;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.leon.pogodynka.R;
import com.example.leon.pogodynka.api.ApiClient;
import com.example.leon.pogodynka.api.ServerResponseListener;
import com.example.leon.pogodynka.api.WSError;
import com.example.leon.pogodynka.api.models.current.ApiCurrentData;
import com.hlab.fabrevealmenu.listeners.OnFABMenuSelectedListener;
import com.hlab.fabrevealmenu.view.FABRevealMenu;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherFragment extends Fragment {

    @BindView(R.id.weatherRecyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.fabMenu)
    FABRevealMenu fabRevealMenu;

    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

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

        fabRevealMenu.bindAncherView(floatingActionButton);
        fabRevealMenu.setOnFABMenuSelectedListener(new OnFABMenuSelectedListener() {
            @Override
            public void onMenuItemSelected(View view) {
                int id = (int) view.getTag();
                Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiClient.getInstance().getCurrent("koszalin", new ServerResponseListener<ApiCurrentData>() {
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
        weatherItemAdapter.add(new WeatherItem(data));
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
