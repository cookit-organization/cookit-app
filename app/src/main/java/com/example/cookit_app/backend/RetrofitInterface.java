package com.example.cookit_app.backend;

import com.example.cookit_app.backend.response.Recipe;
import com.example.cookit_app.backend.response.User;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitInterface {

    String users = "/users", recipes = "/recipes", login = "/login";
    String username = "username", password = "password", collectionId = "collectionId",
            recipeId = "recipeId";


    @POST(login)
    Call<Void> logInUser(@Query(username) String username,
                         @Query(password) String password);

    @POST(users)
    Call<Void> newUser(@Body HashMap<String, String> userData);

    @PUT(users)
    Call<Void> updateUser(@Query(username) String username,
                          @QueryMap HashMap<String, String> userData);

    @DELETE(users)
    Call<Void> deleteUser(@Query(username) String username);

    @GET(users)
    Call<User> getUser(@Query(username) String username);

    @Multipart
    @POST(recipes)
    Call<Void> newRecipe(@QueryMap HashMap<String, String> recipeData,
                         @Part MultipartBody.Part image);

    @PUT(recipes)
    Call<Void> updateRecipe(@Query(collectionId) String collectionId,
                            @Query(recipeId) String recipeId,
                            @QueryMap HashMap<String, String> newData);

    @DELETE(recipes)
    Call<Void> deleteRecipe(@Query(collectionId) String collectionId,
                            @Query(recipeId) String recipeId);

    @GET(recipes + "/random-recipes")
    Call<List<Recipe>> recipesByDayState();

    @GET(recipes + "/by-tag")
    Call<List<Recipe>> getRecipesByTag(@Query("food") String food,
                                       @Query("meal_time") String meal_time);

    @GET(recipes + "/by-single-tag")
    Call<List<Recipe>> getRecipesBySingleTag(@Query("tag") String tag);

    @GET(recipes + "/by-name")
    Call<List<Recipe>> getRecipesByName(@Query("search") String search);

    @POST(recipes + "/vote")
    Call<Void> vote(@Query(recipeId) String recipeId,
                    @Query(username) String username,
                    @Query("vote") int vote);

}
