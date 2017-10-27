package com.example.leon.pogodynka;

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

import com.hlab.fabrevealmenu.listeners.OnFABMenuSelectedListener;
import com.hlab.fabrevealmenu.view.FABRevealMenu;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherFragment extends Fragment {

    @BindView(R.id.weatherRecyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.fabMenu)
    FABRevealMenu fabRevealMenu;

    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;


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

        FastItemAdapter<WeatherItem> fastItemAdapter = new FastItemAdapter<>();
        recyclerView.setAdapter(fastItemAdapter);
        ArrayList<WeatherItem> weatherItems = new ArrayList<>();
        for(int i = 0; i < 14; i++) {
            weatherItems.add(new WeatherItem(String.valueOf(i+1)));
        }
        fastItemAdapter.add(weatherItems);
        fastItemAdapter.withSelectable(true);

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
