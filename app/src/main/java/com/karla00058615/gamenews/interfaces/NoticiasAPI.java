package com.karla00058615.gamenews.interfaces;

import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.classes.Player;
import com.karla00058615.gamenews.classes.Token;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
}
