package com.example.leon.pogodynka.database;

import java.util.List;

/**
 * Created by Leon on 13-Dec-17.
 */

public class Cities {
    List<City> cities;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public Cities(List<City> cities) {
        this.cities = cities;
    }
}
