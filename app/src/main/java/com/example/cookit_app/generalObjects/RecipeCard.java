package com.example.cookit_app.generalObjects;

public class RecipeCard {

    private int recipe_image, save_button;
    private String recipe_name, author_name;

    public RecipeCard(int recipe_image, int save_button, String recipe_name, String author_name) {
        this.recipe_image = recipe_image;
        this.save_button = save_button;
        this.recipe_name = recipe_name;
        this.author_name = author_name;
    }

    public int getRecipe_image() {
        return recipe_image;
    }

    public void setRecipe_image(int recipe_image) {
        this.recipe_image = recipe_image;
    }

    public int getSave_button() {
        return save_button;
    }

    public void setSave_button(int save_button) {
        this.save_button = save_button;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
}
