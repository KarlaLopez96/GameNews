package com.karla00058615.gamenews.classes;

import java.util.ArrayList;

/**
 * Created by Karla on 11/6/2018.
 */

public class User {
    private ArrayList<New> favoriteNews;
    private String _id;
    private String user;
    private String password;
    private String created_date;

    public User(ArrayList<New> favoriteNews, String _id, String user, String password, String created_date) {
        this.favoriteNews = favoriteNews;
        this._id = _id;
        this.user = user;
        this.password = password;
        this.created_date = created_date;
    }

    public ArrayList<New> getFavoriteNews() {
        return favoriteNews;
    }

    public void setFavoriteNews(ArrayList<New> favoriteNews) {
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
}
