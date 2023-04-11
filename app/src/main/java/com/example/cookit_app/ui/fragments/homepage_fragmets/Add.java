package com.example.cookit_app.ui.fragments.homepage_fragmets;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cookit_app.R;
import com.example.cookit_app.generalObjects.Component;
import com.example.cookit_app.generalObjects.RecyclerViewAdapterForAddComponents;
import com.example.cookit_app.generalObjects.SharedPreferencesObject;
import com.example.cookit_app.generalObjects.Tags;
import com.example.cookit_app.server.Retrofit2Init;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.cookit_app.generalObjects.MultiSpinner;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Add extends Fragment{

    MultiSpinner foodTags, meal_timeTags;
    RecyclerView recyclerView;
    List<Component> components;
    EditText recipe_name;
    EditText preparation_time;
    ImageView recipe_image;
    RecyclerViewAdapterForAddComponents rv;
    Uri selectedImage;
    Tags tags;

    @SuppressLint({"ResourceType", "SetTextI18n"}) @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);

        SharedPreferencesObject spo = new SharedPreferencesObject(requireContext());

        recipe_name = view.findViewById(R.id.add_recipe_name_et);
        recipe_name = view.findViewById(R.id.add_recipe_name_et);
        preparation_time = view.findViewById(R.id.add_preparation_time);
        foodTags = view.findViewById(R.id.add_tags);
        meal_timeTags = view.findViewById(R.id.add_meal_time);
        recyclerView = view.findViewById(R.id.add_components_rv);
        ImageButton add_component_ib = view.findViewById(R.id.add_component_ib);
        EditText recipe_details = view.findViewById(R.id.add_recipe_details);
        Button upload_recipe = view.findViewById(R.id.upload_recipe);

        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        recipe_image = view.findViewById(R.id.recipe_image);

        recipe_image.setOnClickListener(v -> {

          Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          startActivityForResult(i, 3);
         });

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

            if (meal_timeTags.getSelectedItem() == tags.mealTimeFirstTag) {
                TextView errorText = (TextView) meal_timeTags.getSelectedView();
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
                recipeData.put("meal_time", meal_timeTags.getSelectedItem().toString());
                recipeData.put("tags", foodTags.getSelectedItem().toString());
                recipeData.put("description", recipe_details.getText().toString());

                File file = new File(selectedImage.getPath());

                RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), reqFile);

                Call<Void> call = new Retrofit2Init().retrofitInterface.newRecipe(recipeData,image);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "The recipe has been uploaded!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
            recipe_image.setImageURI(selectedImage);
        }
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

        tags = new Tags();

        foodTags.setItems(tags.food, tags.foodFirstTag, selected -> {});
        meal_timeTags.setItems(tags.mealTime, tags.mealTimeFirstTag, selected -> {});
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(getContext(), "onSaveInstanceState", Toast.LENGTH_SHORT).show();
        outState.putString("test",recipe_name.getText().toString());
        System.out.println(outState);
    }
}
