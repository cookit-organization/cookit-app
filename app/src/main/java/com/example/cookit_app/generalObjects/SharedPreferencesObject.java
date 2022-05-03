package com.example.cookit_app.generalObjects;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesObject {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public SharedPreferencesObject(Context context) {

        preferences = context.getSharedPreferences("com.example.cookit_app", Context.MODE_PRIVATE);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    public SharedPreferences getPreferences(){
        return preferences;
    }

    // HOW TO USE IT ⤵
//    SharedPreferencesObject spo = new SharedPreferencesObject(getContext()); --> init
//        spo.getPreferences().edit().putBoolean("haha", false); --> put
//        spo.getPreferences().edit().apply(); --> update
//        spo.getPreferences().getBoolean("haha", true); --> get
}

