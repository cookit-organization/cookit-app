package com.example.cookit_app.ui.fragments.homepage_fragmets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookit_app.R;
import com.example.cookit_app.generalObjects.RecyclerViewAdapter;
import com.example.cookit_app.generalObjects.RecipeCard;

import java.util.List;
import java.util.Objects;

public class Explore extends Fragment {

    List<RecipeCard> recipeCards;
    RecyclerView recyclerView;

    @SuppressLint("CommitPrefEdits") @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_fragment, container, false);

        recyclerView = view.findViewById(R.id.rv_container);

        getRecipes();
        recyclerViewAdapter();

        return view;
    }

    private void getRecipes(){
        for(int i = 0; i < 10; i++){
            recipeCards.add(new RecipeCard( R.drawable.default_image, R.drawable.saved, "recipe name", "author name"));
        }
    }

    private void recyclerViewAdapter(){

//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setAdapter(new RecyclerViewAdapter(getContext(), recipeCards));
    }
}
