package com.thedeveloperworldisyours.unitconverterpro;

import android.app.Application;

import com.thedeveloperworldisyours.unitconverterpro.common.utils.PreferencesManager;

/**
 * Created by javierg on 09/09/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        PreferencesManager.initializeInstance(getApplicationContext());
    }
}
