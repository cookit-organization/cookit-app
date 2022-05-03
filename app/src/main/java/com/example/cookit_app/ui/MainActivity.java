package com.example.cookit_app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import com.example.cookit_app.R;
import com.example.cookit_app.ui.fragments.homepage_fragmets.Add;
import com.example.cookit_app.ui.fragments.homepage_fragmets.Explore;
import com.example.cookit_app.ui.fragments.homepage_fragmets.Home;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: check if there is sharedPreferences of his User Id if, not take him to login page
        //3 fragments of the app (explore, add, home)

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