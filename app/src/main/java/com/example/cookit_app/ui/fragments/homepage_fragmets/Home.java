package com.example.cookit_app.ui.fragments.homepage_fragmets;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookit_app.R;
import com.example.cookit_app.generalObjects.RecyclerViewAdapter;
import com.example.cookit_app.generalObjects.SharedPreferencesObject;
import com.example.cookit_app.server.responseObjects.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Home extends Fragment{

    List<Recipe> recipeCards;

    @SuppressLint("SetTextI18n") @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        //check from sharedPreferences list for example : [meat, soup, etc..]
        //take from database the wanted categories (from general data)

        //make list of recyclerViews for the categories

        /*
        *
        * meat ->
        * 1 2 3 4 5 6 7
        *
        * soup ->
        * 1 2 3 4 5 6 7
        *
        * fish ->
        * 1 2 3 4 5 6 7
        *
        * */

        recipeCards = new ArrayList<>();

        //get info from sharedPreferences
        SharedPreferencesObject spo = new SharedPreferencesObject(requireContext());


        LinearLayout ll = view.findViewById(R.id.wanted_categories_container);

        for (int i = 0; i < 6; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(" hello world");
            textView.setTextSize(30);
            textView.setTextColor(Color.BLACK);

            RecyclerView rv = new RecyclerView(requireContext());
            rv.setLayoutParams(new
                    RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT
            ));

            ll.addView(textView);
            ll.addView(rv);

            getRecipes();
            recyclerViewAdapter(rv);
        }
        return view;
    }

    private void getRecipes(){
      //get recipes from database
    }

    private void recyclerViewAdapter(RecyclerView recyclerView){

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new RecyclerViewAdapter(getContext(), recipeCards));
    }
}
