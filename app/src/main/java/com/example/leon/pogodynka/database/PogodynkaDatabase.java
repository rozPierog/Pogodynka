package com.example.leon.pogodynka.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Leon on 13-Dec-17.
 */

@Database(entities = {City.class}, version = 1)
public abstract class PogodynkaDatabase extends RoomDatabase {
    public abstract CityDao cityDao();
}
