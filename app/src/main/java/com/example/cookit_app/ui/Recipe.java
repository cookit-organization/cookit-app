package com.example.cookit_app.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookit_app.R;

public class Recipe extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);
    }
}
