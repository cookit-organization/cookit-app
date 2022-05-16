package com.example.cookit_app.ui.fragments.homepage_fragmets;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.cookit_app.R;

public class Add extends Fragment {

    Spinner tags, meal_time;

    private final int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

    @SuppressLint("ResourceType") @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);

        tags = view.findViewById(R.id.tags);
        meal_time = view.findViewById(R.id.meal_time);

        // if pressed on add button check if component is not empty if not take the values and then do this again
        // if pressed on remove button remove current card

        return view;
    }


}
