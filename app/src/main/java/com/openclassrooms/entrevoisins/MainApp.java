package com.openclassrooms.entrevoisins;

import android.app.Application;

import com.openclassrooms.entrevoisins.di.DI;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DI.getUserPref().init(this);
    }
}
