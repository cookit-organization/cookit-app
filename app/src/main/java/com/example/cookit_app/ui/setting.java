package com.example.cookit_app.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cookit_app.R;
import com.example.cookit_app.generalObjects.MultiSpinner;

import java.util.ArrayList;
import java.util.List;

public class setting extends AppCompatActivity {

    MultiSpinner tags, favorite_categories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        tags = findViewById(R.id.tags);
        favorite_categories = findViewById(R.id.favorite_categories);
        createSpinners();

    }

    private void createSpinners(){

        List<String> listTags = new ArrayList<>();
        listTags.add("meat");
        listTags.add("soup");
        listTags.add("fish");

        List<String> listFavoriteCategories = new ArrayList<>();
        listFavoriteCategories.add("Morning");
        listFavoriteCategories.add("Afternoon");
        listFavoriteCategories.add("Night");

        tags.setItems(listTags, "disable tags", selected -> {});
        favorite_categories.setItems(listFavoriteCategories, "favorite categories", selected -> {});
    }
}
