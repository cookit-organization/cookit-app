package com.example.cookit_app.ui.fragments.homepage_fragmets;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cookit_app.R;
import com.example.cookit_app.generalObjects.Component;
import com.example.cookit_app.generalObjects.RecyclerViewAdapterForAddComponents;
import com.example.cookit_app.generalObjects.SharedPreferencesObject;
import com.example.cookit_app.server.Retrofit2Init;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.cookit_app.generalObjects.MultiSpinner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Add extends Fragment{

    MultiSpinner tags, meal_time;
    RecyclerView recyclerView;
    List<Component> components;
    RecyclerViewAdapterForAddComponents rv;

    @SuppressLint({"ResourceType", "SetTextI18n"}) @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);

        SharedPreferencesObject spo = new SharedPreferencesObject(requireContext());

        EditText recipe_name = view.findViewById(R.id.add_recipe_name_et);
        EditText preparation_time = view.findViewById(R.id.add_preparation_time);
        tags = view.findViewById(R.id.add_tags);
        meal_time = view.findViewById(R.id.add_meal_time);
        recyclerView = view.findViewById(R.id.add_components_rv);
        ImageButton add_component_ib = view.findViewById(R.id.add_component_ib);
        EditText recipe_details = view.findViewById(R.id.add_recipe_details);
        Button upload_recipe = view.findViewById(R.id.upload_recipe);

        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        //todo: arthur => upload an image https://github.com/orgs/cookit-organization/projects/2#card-81988242

        // spinners
        createSpinners();

        //recyclerview adapter for add components
        components = new ArrayList<>();

        addComponent();
        recyclerViewAdapter();

        add_component_ib.setOnClickListener(v -> {
               addComponent();
               recyclerViewAdapter();
        });

        upload_recipe.setOnClickListener(v -> {
            boolean correct = true;
            String recipeName = recipe_name.getText().toString();

            if (recipeName.equals("")) {
                recipe_name.setError("Field is empty");
                correct = false;
            }

            if (meal_time.getSelectedItem() == "pick Meal time") {
                TextView errorText = (TextView) meal_time.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);
                errorText.setText("Field is empty");
                correct = false;
            }

            for (int i = 0; i < rv.getViewHolders().size(); i++) {

                String comp = rv.getViewHolders().get(i).component_et.getText().toString();
                String amount = rv.getViewHolders().get(i).amount_et.getText().toString();

                if (comp.equals("")) {
                    rv.getViewHolders().get(i).component_et.setError("Field is empty");
                    correct = false;
                }
                if (amount.equals("")) {
                    rv.getViewHolders().get(i).amount_et.setError("Field is empty");
                    correct = false;
                }
            }

            if (correct) {
                progressBar.setVisibility(View.VISIBLE);

                HashMap<String, String> recipeData = new HashMap<>();

                recipeData.put("author_username", spo.getPreferences().getString(spo.username, "shmuel"));
                recipeData.put("name", recipeName);
                recipeData.put("preparation_time", preparation_time.getText().toString());
                recipeData.put("mea_time", meal_time.getSelectedItem().toString());
                recipeData.put("tags", tags.getSelectedItem().toString());
                recipeData.put("description", recipe_details.getText().toString());
                recipeData.put("image", null); // still thinking what to do..

                Call<Void> call = new Retrofit2Init().retrofitInterface.newRecipe(recipeData);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        progressBar.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            Toast.makeText(requireContext(), "Yay!\n" + response.body(), Toast.LENGTH_SHORT).show();
                            //recipe uploaded
                        } else {
                            Toast.makeText(requireContext(), "Nooo!\n" + response.message(), Toast.LENGTH_SHORT).show();
                            //check reasons
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        //check reason
                    }
                });
            }
        });

        return view;
    }

    private void addComponent(){
        components.add(new Component(null, null));
    }

    private void recyclerViewAdapter(){

        rv = new RecyclerViewAdapterForAddComponents(getContext(), components);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(rv);
    }

    private void createSpinners(){

        List<String> listTags = new ArrayList<>();
        listTags.add("meat");
        listTags.add("soup");
        listTags.add("fish");

        List<String> listMealTime = new ArrayList<>();
        listMealTime.add("Morning");
        listMealTime.add("Afternoon");
        listMealTime.add("Night");

        tags.setItems(listTags, "pick Tags", selected -> {});
        meal_time.setItems(listMealTime, "pick Meal time", selected -> {});
    }

}
