package com.example.cookit_app.ui.fragments;
import android.widget.RatingBar;
import android.widget.Toast;

public class Rate {

    private final RatingBar ratingBar;

    public Rate(RatingBar ratingBar) {
        this.ratingBar = ratingBar;
        //TODO: check current rateBar's rate from database (take from recipe's data "average_rate")

        ratingBar.setOnRatingBarChangeListener((rb, rating, byUser) -> {
            Toast.makeText(rb.getContext(), String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();
            updateDB();
        });
    }

    public void updateDB(){
        //TODO: make sure to change in database
        //getRatingBar().getRating()
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRate(float ratingBar) {
        this.ratingBar.setRating(ratingBar);
    }
}
