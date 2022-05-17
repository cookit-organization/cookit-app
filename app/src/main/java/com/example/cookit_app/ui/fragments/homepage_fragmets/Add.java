package com.example.cookit_app.ui.fragments.homepage_fragmets;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cookit_app.R;
import com.example.cookit_app.generalObjects.Component;
import com.example.cookit_app.generalObjects.RecyclerViewAdapterForAddComponents;
import com.example.cookit_app.ui.MultiSpinner;
import java.util.ArrayList;
import java.util.List;

public class Add extends Fragment{

    MultiSpinner tags, meal_time;
    RecyclerView recyclerView;
    List<Component> components;
    RecyclerViewAdapterForAddComponents rv;

    @SuppressLint("ResourceType") @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);


        tags = view.findViewById(R.id.tags);
        meal_time = view.findViewById(R.id.meal_time);
        recyclerView = view.findViewById(R.id.add_components_rv);
        ImageButton add_component_ib = view.findViewById(R.id.add_component_ib);

        // spinners

        List<String> listTags = new ArrayList<>();

        listTags.add("meat");
        listTags.add("soup");
        listTags.add("fish");

        tags.setItems(listTags, "pick Tags", selected -> {});

        List<String> listMealTime = new ArrayList<>();

        listMealTime.add("Morning");
        listMealTime.add("Afternoon");
        listMealTime.add("Night");

        meal_time.setItems(listMealTime, "pick Meal time", selected -> {});

        //recyclerview adapter for add components


        components = new ArrayList<>();

        addComponent();
        recyclerViewAdapter();

        //add component button
        //to get the list of the componenets

        add_component_ib.setOnClickListener(v -> {
            addComponent();
            recyclerViewAdapter();
            Toast.makeText(requireContext(), rv.getList().size() + "", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void addComponent(){
        components.add(new Component(null, "test"));
    }

    private void recyclerViewAdapter(){
        rv = new RecyclerViewAdapterForAddComponents(getContext(), components);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(rv);
    }


}
