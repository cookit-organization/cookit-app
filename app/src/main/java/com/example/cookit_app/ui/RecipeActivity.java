package com.example.cookit_app.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookit_app.R;
import com.example.cookit_app.server.responseObjects.Recipe;

import java.io.Serializable;

public class RecipeActivity extends AppCompatActivity implements Serializable {

    @SuppressLint("NonConstantResourceId") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        ImageView recipe_image = findViewById(R.id.recipe_image);
//        recipe_image.setImageResource();

        TextView recipe_author = findViewById(R.id.recipe_author);
        recipe_author.setText(recipe.getAuthor_name());

        TextView recipe_name = findViewById(R.id.recipe_name);
        recipe_name.setText(recipe.recipe.getName());

        TextView recipe_description = findViewById(R.id.recipe_description);
        recipe_description.setText(recipe.recipe.getDescription());

    }
}
