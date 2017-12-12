package com.example.leon.pogodynka;

import android.app.Application;
import android.content.Context;

/**
 * Created by Leon on 12-Dec-17.
 */

public class PogodynkaApp extends Application {
    private static PogodynkaApp pogodynkaApp;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        pogodynkaApp = this;
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static PogodynkaApp getPogodynkaApp() {
        return pogodynkaApp;
    }
}
