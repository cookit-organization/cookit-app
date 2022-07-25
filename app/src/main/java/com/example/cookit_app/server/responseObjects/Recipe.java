package com.example.cookit_app.server.responseObjects;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {

    private String _id, author_username, author_name;
    public RecipeDetails recipe;

    public String get_id() {
        return _id;
    }

    public String getAuthor_username() {
        return author_username;
    }

    public String getAuthor_name() {
        return author_name;
    }

    @NonNull @Override
    public String toString() {
        return "RecipeActivity [\n" +
                "_id='" + _id + '\'' +
                ", author_username='" + author_username + '\'' +
                ", recipe_name='" + recipe.name + '\'' +
                ", description='" + recipe.description + '\'' +
                ", image='" + recipe.image + '\'' +
                ", mealtime=" + recipe.mealtime +
                ", tags=" + recipe.tags +
                ", average_rate=" + recipe.average_rate +
                ", rates_number=" + recipe.rates_number +
                "\n]";
    }

    public static class RecipeDetails implements Serializable {

        private String name, description, image;
        @SerializedName("meal_time") private List<String> mealtime;
        private List<String> tags;
        private Number average_rate, rates_number;


        public String getName() {
            return name;
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
}
