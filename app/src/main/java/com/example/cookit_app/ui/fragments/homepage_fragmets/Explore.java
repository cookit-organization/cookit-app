package com.example.cookit_app.ui.fragments.homepage_fragmets;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookit_app.R;
import com.example.cookit_app.utils.GridSpacingItemDecoration;
import com.example.cookit_app.utils.MultiSpinner;
import com.example.cookit_app.utils.RecyclerViewAdapterForRecipes;
import com.example.cookit_app.utils.Tags;
import com.example.cookit_app.backend.Retrofit2Init;
import com.example.cookit_app.backend.RetrofitInterface;
import com.example.cookit_app.backend.response.Recipe;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class Explore extends Fragment {

    final String fieldIsEmpty = "Field is Empty";
    List<Recipe> recipeCards;
    RecyclerView recyclerView;
    RetrofitInterface retrofitInterface;
    MultiSpinner foodTags, mealTimeTags;
    Tags tags;

    @SuppressLint("SetTextI18n") @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_fragment, container, false);

        retrofitInterface = new Retrofit2Init().retrofitInterface;
        recyclerView = view.findViewById(R.id.rv_container);
        recipeCards = new ArrayList<>();
        recyclerViewAdapter();

        LinearLayout searchOptionContainer = view.findViewById(R.id.search_option_container);

        MaterialButton search_options = view.findViewById(R.id.more_search_options);
        search_options.setOnClickListener(v -> {

            if (searchOptionContainer.getVisibility() == View.GONE) {
                searchOptionContainer.setVisibility(View.VISIBLE);
                search_options.setIconResource(R.drawable.ic_point_up_24);
            } else {
                searchOptionContainer.setVisibility(View.GONE);
                search_options.setIconResource(R.drawable.ic_point_down_24);
            }

            foodTags = view.findViewById(R.id.food_tags);
            mealTimeTags = view.findViewById(R.id.meal_time_tags);
            createSpinners();

            Button search = view.findViewById(R.id.search_tags_button);
            search.setOnClickListener(v1 -> {
                boolean isValidInput = validateUserInput();
                if (isValidInput) {
                    getRecipes(
                            retrofitInterface.getRecipesByTag(
                            foodTags.getSelectedItem().toString(),
                            mealTimeTags.getSelectedItem().toString()),
                            false);
                }
            });
        });

        SearchView searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String search) {
                getRecipes(retrofitInterface.getRecipesByName(search), false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String ignored) {
                return false;
            }
        });

        getRecipes(retrofitInterface.recipesByDayState(), true);
        return view;
    }

    private boolean validateUserInput() {
        if (mealTimeTags.getSelectedItem() == tags.mealTimeFirstTag) {
            TextView errorText = (TextView) mealTimeTags.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText(fieldIsEmpty);
            return false;
        }
        if (foodTags.getSelectedItem() == tags.foodFirstTag) {
            TextView errorText = (TextView) foodTags.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText(fieldIsEmpty);
            return false;
        }
        return true;
    }

    private void createSpinners() {
        tags = new Tags();
        foodTags.setItems(tags.food, tags.foodFirstTag, selected -> {});
        mealTimeTags.setItems(tags.mealTime, tags.mealTimeFirstTag, selected -> {});
    }

    private void getRecipes(Call<List<Recipe>> call, boolean add) {

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful()) {
                    if (!add) {
                        recipeCards.clear();
                    }
                    recipeCards.addAll(response.body());
                    updateAdapter();
                } else if (response.code() == HTTP_NOT_FOUND) {
                    makeToast("Not Found.");
                } else
                    makeToast(response.message());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                makeToast("Sorry something went wrong. we are on it !");
            }
        });
    }

    private void recyclerViewAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(
                2, 100, false));
    }

    private void updateAdapter() {
        recyclerView.setAdapter(new RecyclerViewAdapterForRecipes(getContext(), recipeCards));
    }

    private void makeToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }
}
