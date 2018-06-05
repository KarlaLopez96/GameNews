package com.karla00058615.gamenews.classes;

/**
 * Created by Karla on 3/6/2018.
 */

public class New {
    private int id;
    private String title;
    private String body;
    private String game;
    private String convertImg;
    private String created_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getConvertImg() {
        return convertImg;
    }

    public void setConvertImg(String convertImg) {
        this.convertImg = convertImg;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
