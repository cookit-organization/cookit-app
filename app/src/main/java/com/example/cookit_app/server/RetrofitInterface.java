package com.example.cookit_app.server;

import com.example.cookit_app.server.responseObjects.User;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitInterface {

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
}
