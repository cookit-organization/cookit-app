package com.example.cookit_app.server.responseObjects;

import java.util.List;

public class Recipe {

    private String _id, author_name,author_username, recipe_name, description, image;
    private List<String> mealtime, tags;
    private Number average_rate, rates_number;

    public String get_id() {
        return _id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getAuthor_username() {
        return author_username;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public List<String> getMealtime() {
        return mealtime;
    }

    public List<String> getTags() {
        return tags;
    }

    public Number getAverage_rate() {
        return average_rate;
    }

    public Number getRates_number() {
        return rates_number;
    }
}
