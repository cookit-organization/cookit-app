package com.example.cookit_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    private RatingBar stars;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stars = findViewById(R.id.rating);
        button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            rateFragment dialogFragment = new rateFragment();
            dialogFragment.show(getSupportFragmentManager(), "dialog fragment");
        });
    }
}