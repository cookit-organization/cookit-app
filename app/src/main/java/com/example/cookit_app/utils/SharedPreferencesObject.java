package com.example.cookit_app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesObject {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    //sharedPreferences names ⤵

    public final String
            isSignedUp = "is_signed_up",
            wantedTagsList = "wanted_tags_list",
            username = "username";


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
// In order to save the data you must to update, therefore you need to use spo.getPreferences().edit().apply();
}

