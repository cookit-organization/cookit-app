package com.example.cookit_app.ui.fragments.homepage_fragmets;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cookit_app.R;
import com.example.cookit_app.generalObjects.GridSpacingItemDecoration;
import com.example.cookit_app.generalObjects.RecyclerViewAdapter;
import com.example.cookit_app.server.Retrofit2Init;
import com.example.cookit_app.server.RetrofitInterface;
import com.example.cookit_app.server.responseObjects.Recipe;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Explore extends Fragment {

    List<Recipe> recipeCards;
    RecyclerView recyclerView;


    @SuppressLint("CommitPrefEdits") @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_fragment, container, false);

        RetrofitInterface retrofitInterface = new Retrofit2Init().retrofitInterface;

        recyclerView = view.findViewById(R.id.rv_container);
        recipeCards = new ArrayList<>();

        SearchView searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String search) {
                Toast.makeText(requireContext(), search, Toast.LENGTH_SHORT).show();
                getRecipes(retrofitInterface.getRecipeByName(search));
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                //we can use this to get the rest of the word or
                //to give him suggestion for recipes that has the same prefix
                return false;
            }
        });

        //default get random recipes (by timezone)
        if(recipeCards.isEmpty()){
            getRecipes(retrofitInterface.getRandomRecipe());
        }

        return view;
    }

    private void getRecipes(Call<List<Recipe>> call){

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(response.isSuccessful()) {
                    recipeCards.addAll(response.body());
                    recyclerViewAdapter();
                    recipeCards.clear();
                }
                else
                    Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(getContext(), "Sorry something went wrong. we are on it !", Toast.LENGTH_SHORT).show();
                //TODO : only if we have time -> send error to errorsServer (a server for error handling)
            }
        });
    }

    private void recyclerViewAdapter(){

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 100, false));
        recyclerView.setAdapter(new RecyclerViewAdapter(getContext(), recipeCards));
    }
}
