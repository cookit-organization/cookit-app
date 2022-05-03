package com.example.cookit_app.ui.fragments.homepage_fragmets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cookit_app.R;
import com.example.cookit_app.ui.LoginActivity;
import com.example.cookit_app.ui.fragments.Rate;

public class Explore extends Fragment{

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_fragment, container, false);

//        new Rate(view.findViewById(R.id.rate_bar));

        startActivity(new Intent(getContext(), LoginActivity.class));
        return view;
    }
}
