package com.example.cookit_app.ui.fragments.homepage_fragmets;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookit_app.R;
import com.example.cookit_app.backend.Retrofit2Init;
import com.example.cookit_app.backend.response.Recipe;
import com.example.cookit_app.ui.Setting;
import com.example.cookit_app.utils.RecyclerViewAdapterForRecipes;
import com.example.cookit_app.utils.SharedPreferencesObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    List<Recipe> recipeCards;
    LinearLayout ll;
    Button setting;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        setting = view.findViewById(R.id.setting);
        setting.setOnClickListener(v -> startActivity(new Intent(getContext(), Setting.class)));

        recipeCards = new ArrayList<>();

        SharedPreferencesObject spo = new SharedPreferencesObject(requireContext());

        ll = view.findViewById(R.id.wanted_categories_container);


        //HashSet<>() is the list of the wanted tags that we save in the sharedP
        HashSet<String> s = new HashSet<>();
        spo.getPreferences().edit().putStringSet(spo.wantedTagsList, s).apply();

        List<String> tags = new ArrayList<>(spo.getPreferences().getStringSet(spo.wantedTagsList, new HashSet<>()));

        if (!tags.isEmpty()) {
            for (String tag : tags) {
                Call<List<Recipe>> call = new Retrofit2Init().retrofitInterface.getRecipesBySingleTag(tag);

                call.enqueue(new Callback<List<Recipe>>() {
                    @Override
                    public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                        if (response.isSuccessful()) {
                            recipeCards.addAll(response.body());
                            createList(tag);
                            recipeCards.clear();
                        } else
                            Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<Recipe>> call, Throwable t) {
                        Toast.makeText(getContext(), "Sorry something went wrong. we are on it !", Toast.LENGTH_SHORT).show();
                        //TODO : only if we have time -> send error to errorsServer (a server for error handling

                        //                        ShapeableImageView si = new ShapeableImageView(requireContext());
                        //                        si.setLayoutParams(new LinearLayout.LayoutParams(
                        //                                LinearLayout.LayoutParams.MATCH_PARENT,
                        //                                LinearLayout.LayoutParams.MATCH_PARENT
                        //                        ));
                        //                        si.setImageResource(R.drawable.cookit_app_logo);
                        //                        si.setShapeAppearanceModel(si.getShapeAppearanceModel().toBuilder().
                        //                                setTopRightCorner(CornerFamily.ROUNDED,30).build());
                        //                        ll.addView(si);
                        //                        ll.setGravity(Gravity.CENTER_VERTICAL);
                    }
                });
            }
        }

        return view;
    }

    private void createList(String tag) {
        TextView textView = new TextView(getContext());
        textView.setText(tag);
        textView.setTextSize(30);
        textView.setTextColor(Color.BLACK);

        RecyclerView rv = new RecyclerView(requireContext());
        rv.setLayoutParams(new
                RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));

        ll.addView(textView);
        ll.addView(rv);

        recyclerViewAdapter(rv);
    }

    private void recyclerViewAdapter(RecyclerView recyclerView) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new RecyclerViewAdapterForRecipes(getContext(), recipeCards));
    }
}
