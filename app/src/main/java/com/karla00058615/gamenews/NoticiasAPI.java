package com.karla00058615.gamenews;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Karla on 3/6/2018.
 */

public interface NoticiasAPI {

    @GET("/news")
    void getNews(Callback<List<New>> callback);


    @FormUrlEncoded
    @POST("/login")
    Call<Token> getToken(@Field("user") String User, @Field("password") String Pass);
}
