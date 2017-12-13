package com.example.leon.pogodynka;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.leon.pogodynka.database.PogodynkaDatabase;
import com.facebook.stetho.Stetho;

/**
 * Created by Leon on 12-Dec-17.
 */

public class PogodynkaApp extends Application {

    private static PogodynkaApp pogodynkaApp;
    private static Context context;
    private PogodynkaDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        pogodynkaApp = this;
        context = getApplicationContext();
        database = Room.databaseBuilder(getApplicationContext(), PogodynkaDatabase.class,
                "POGODYNKA_DATABASE").build();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    public Context getContext() {
        return context;
    }

    public static PogodynkaApp get() {
        return pogodynkaApp;
    }

    public PogodynkaDatabase getDB() {
        return database;
    }
}
