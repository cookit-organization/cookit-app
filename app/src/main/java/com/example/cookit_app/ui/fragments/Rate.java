package com.example.cookit_app.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.cookit_app.R;

public class Rate extends DialogFragment {

    private RatingBar stars;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rate_fragment, container, false);

        //TODO: check current rateBar's rate from database (take from recipe's data "average_rate")

        stars = view.findViewById(R.id.rating);
        stars.setOnRatingBarChangeListener((ratingBar, rating, byUser) -> {
            stars.setRating(rating);
            //TODO: make sure to change in database
        });

        return view;
    }
}
