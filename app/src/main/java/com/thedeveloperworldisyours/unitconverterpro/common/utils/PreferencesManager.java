package com.thedeveloperworldisyours.unitconverterpro.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by javierg on 09/09/16.
 */
public class PreferencesManager {
    private static final String PREF_NAME = "com.example.app.PREF_NAME";
    private static final String UPDATE = "com.thedeveloperworldisyours.unitconverterpro.UPDATE";

    private static PreferencesManager sInstance;
    private final SharedPreferences mSharedPreferences;

    private PreferencesManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setUpdate(String date) {
        mSharedPreferences.edit()
                .putString(UPDATE, date)
                .commit();
    }

    public String getUpdate() {
        return mSharedPreferences.getString(UPDATE, "");
    }

    public void remove(String key) {
        mSharedPreferences.edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return mSharedPreferences.edit()
                .clear()
                .commit();
    }
}
