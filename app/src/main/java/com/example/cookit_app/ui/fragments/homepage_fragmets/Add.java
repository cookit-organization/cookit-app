package com.example.cookit_app.ui.fragments.homepage_fragmets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.cookit_app.R;

public class Add extends Fragment {

    Spinner tags, meal_time;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);

        tags = view.findViewById(R.id.tags);
        meal_time = view.findViewById(R.id.meal_time);

        return view;
    }

}
