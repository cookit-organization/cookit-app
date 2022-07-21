package com.example.cookit_app.server.responseObjects;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Recipe implements Parcelable {

    private final String _id, author_username, author_name;
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

    protected Recipe(Parcel in) {
        _id = in.readString();
        author_username = in.readString();
        author_name = in.readString();
        recipe = in.readParcelable(RecipeDetails.class.getClassLoader());
    }

    @NonNull @Override
    public String toString() {
        return "Recipe [\n" +
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

    public static class RecipeDetails implements Parcelable{

        private final String name, description, image;
        @SerializedName("meal_time")
        private final List<String> mealtime;
        private final List<String> tags;
        private Number average_rate, rates_number;

        protected RecipeDetails(Parcel in) {
            name = in.readString();
            description = in.readString();
            image = in.readString();
            mealtime = in.createStringArrayList();
            tags = in.createStringArrayList();
        }

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name);
            parcel.writeString(description);
            parcel.writeString(image);
            parcel.writeStringList(mealtime);
            parcel.writeStringList(tags);
            parcel.writeValue(average_rate);
            parcel.writeValue(rates_number);
        }

        public final Creator<RecipeDetails> CREATOR = new Creator<RecipeDetails>() {
            @Override
            public RecipeDetails createFromParcel(Parcel in) {
                return new RecipeDetails(in);
            }

            @Override
            public RecipeDetails[] newArray(int size) {
                return new RecipeDetails[size];
            }
        };

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(author_username);
        parcel.writeValue(recipe);
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

}
