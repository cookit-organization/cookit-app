package com.example.cookit_app.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cookit_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: check if there is sharedPreferences of his User Id if, not take him to login page

        //TODO: 3 fragments of the app (explore, add, home)

    }
}