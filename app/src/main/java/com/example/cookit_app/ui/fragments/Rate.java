package com.example.cookit_app.ui.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cookit_app.R;

public class Rate extends Fragment {

    private RatingBar ratingBar;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rate_fragment, container, false); //todo: change the layout

        //TODO: check current rateBar's rate from database (take from recipe's data "average_rate")
        ratingBar = view.findViewById(R.id.rate_bar);
        ratingBar.setOnRatingBarChangeListener((rb, rating, byUser) -> {
            Toast.makeText(rb.getContext(), String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();
            updateDB();
        });
        return view;
    }

    public void updateDB(){
        //TODO: ➖ send the rate vote to the server
        //      ➖ add it to the average
        //      ➖ update in database
        //getRatingBar().getRating()
    }
}
