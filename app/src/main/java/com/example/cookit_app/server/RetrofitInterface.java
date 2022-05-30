package com.example.cookit_app.server;

import com.example.cookit_app.server.responseObjects.Recipe;
import com.example.cookit_app.server.responseObjects.User;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitInterface {

    //user

    @POST("/users/new-user")
    Call<Void> newUser(@QueryMap HashMap<String, String> userData);

    @PUT("/users/update-user")
    Call<Void> updateUser(@Query("username") String username, @QueryMap HashMap<String, String> newDAta);

    @DELETE("/users/delete-user")
    Call<Void> deleteUser(@Query("username") String username); // delete his recipes as well

    @GET("/users/get-user")
    Call<User> getUSer(@Query("username") String username);

    @POST("/users/log-in-user")
    Call<Void> logInUser(@Query("username") String username, @Query("password") String password);

    //recipes

    @POST("/recipes/new-recipe")
    Call<Void> newRecipe(@QueryMap HashMap<String, String> recipeData);

    @PUT("/recipes/update-recipe")
    Call<Void> updateRecipe(@Query("collectionId") String collectionId, @Query("recipeId") String recipeId, @QueryMap HashMap<String, String> newData);

    @DELETE("/recipes/delete-recipe")
    Call<Void> deleteRecipe(@Query("collectionId") String collectionId, @Query("recipeId") String recipeId);

    @GET("/recipes/random-recipes")
    Call<List<Recipe>> getRandomRecipe();

    @GET("/recipes/recipe-by-tag")
    Call<List<Recipe>> getRecipesByTag(@QueryMap List<String> tags);

    @GET("/recipes/recipes-by-single-tag")
    Call<List<Recipe>> getRecipeBySingleTag(@Query("tag") String tag);

    @GET("/recipes/get-recipe-by-name")
    Call<List<Recipe>> getRecipeByName(@Query("name") String name);

}
