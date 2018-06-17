package com.karla00058615.gamenews.interfaces;

import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.classes.NewFav;
import com.karla00058615.gamenews.classes.Player;
import com.karla00058615.gamenews.classes.Token;
import com.karla00058615.gamenews.classes.User;
import com.karla00058615.gamenews.classes.UserDB;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Karla on 3/6/2018.
 */

public interface NoticiasAPI {


    @GET("/news")
    Call<List<New>> getNews(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("/login")
    Call<Token> getToken(@Field("user") String User, @Field("password") String Pass);

    @GET("/players")
    Call<List<Player>> getPlayers(@Header("Authorization") String token);

    @GET("/news/type/list")
    Call<List<String>> getCategory(@Header("Authorization") String token);

    @GET("/users")
    Call<List<User>> getUsersList(@Header("Authorization") String token);

    @GET("/users/detail")
    Call<User> getUserInfo(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("/users/{userId}/fav")
    Call<NewFav> addFav(@Header("Authorization") String token, @Path("userId") String userId, @Field("new") String new_id);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/users/{userId}/fav", hasBody = true)
    Call<String> removeFav(@Header("Authorization") String token, @Path("userId") String userId, @Field("new") String new_id);

    @FormUrlEncoded
    @PUT("/users/{userId}")
    Call<User> updateUser(@Header("Authorization") String token, @Path("userId") String userId, @Field("password") String new_id);
}
