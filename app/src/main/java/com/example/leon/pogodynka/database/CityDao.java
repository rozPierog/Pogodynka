package com.example.leon.pogodynka.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


/**
 * Created by Leon on 13-Dec-17.
 */

@Dao
public interface CityDao {

    @Query("SELECT * FROM City")
    List<City> getAll();

    @Query("SELECT * FROM City WHERE name LIKE :name")
    List<City> findByName(String name);

    @Query("DELETE FROM city")
    void nukeTable();

    @Delete
    void delete(City city);

    @Insert
    void insertAll(List<City> cities);
}
