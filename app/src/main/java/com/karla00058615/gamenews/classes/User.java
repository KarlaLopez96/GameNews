package com.karla00058615.gamenews.classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Karla on 11/6/2018.
 */

@Entity (tableName = "User")
public class User {

    private ArrayList<String> favoriteNews;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String _id;

    @ColumnInfo(name = "favorits")
    private String favoriteN;

    @ColumnInfo(name = "user")
    private String user;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "created_date")
    private String created_date;

    public User(ArrayList<String> favoriteNews, String _id, String user, String password, String created_date) {
        this.favoriteNews = favoriteNews;
        this._id = _id;
        this.user = user;
        this.password = password;
        this.created_date = created_date;
    }

    public ArrayList<String> getFavoriteNews() {
        return favoriteNews;
    }

    public void setFavoriteNews(ArrayList<String> favoriteNews) {
        this.favoriteNews = favoriteNews;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getFavoriteN() {
        Gson gson = new Gson();
        favoriteN = gson.toJson(favoriteNews);
        return favoriteN;
    }

    public void setFavoriteN(ArrayList<String> favoriteN) {
        Gson gson = new Gson();
        this.favoriteN = gson.toJson(favoriteN);
    }
}
