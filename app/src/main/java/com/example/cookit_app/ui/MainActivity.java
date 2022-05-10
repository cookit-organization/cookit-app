package com.example.cookit_app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import com.example.cookit_app.R;
import com.example.cookit_app.generalObjects.SharedPreferencesObject;
import com.example.cookit_app.ui.fragments.homepage_fragmets.Add;
import com.example.cookit_app.ui.fragments.homepage_fragmets.Explore;
import com.example.cookit_app.ui.fragments.homepage_fragmets.Home;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferencesObject spo = new SharedPreferencesObject(this);

        if(!spo.getPreferences().getBoolean(spo.isSignedUp, false))
            startActivity(new Intent(this, LoginActivity.class));

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new Home();
                    break;
                case R.id.nav_add:
                    selectedFragment = new Add();
                    break;
                case R.id.nav_explore:
                    selectedFragment = new Explore();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });

        //default: explore fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Explore()).commit();
        }
    }

}