package com.example.leon.pogodynka.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Leon on 13-Dec-17.
 */
@Entity(tableName = "City")
public class City {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "country")
    @SerializedName("country")
    private String country;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
