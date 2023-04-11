package com.example.cookit_app.utils;

import java.util.ArrayList;
import java.util.List;

public class Tags {

    public List<String> food = new ArrayList<>();
    public List<String> mealTime = new ArrayList<>();

    public String foodFirstTag = "Pick Meal Time", mealTimeFirstTag = "Pick Food Categories";


    public Tags() {
        food.add("Meat");
        food.add("Soup");
        food.add("Fish");

        mealTime.add("Morning");
        mealTime.add("Afternoon");
        mealTime.add("Night");
    }
}
