package com.karla00058615.gamenews.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karla on 3/6/2018.
 */

public class New implements Parcelable{
    private int id;
    private String title;
    private String body;
    private String game;
    private String convertImg;
    private String created_date;

    public New(int id, String title, String body, String game, String convertImg, String created_date) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.game = game;
        this.convertImg = convertImg;
        this.created_date = created_date;
    }

    protected New(Parcel in) {
        id = in.readInt();
        title = in.readString();
        body = in.readString();
        game = in.readString();
        convertImg = in.readString();
        created_date = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(body);
        dest.writeString(game);
        dest.writeString(convertImg);
        dest.writeString(created_date);
    }

    public static final Creator<New> CREATOR = new Creator<New>() {
        @Override
        public New createFromParcel(Parcel in) {
            return new New(in);
        }

        @Override
        public New[] newArray(int size) {
            return new New[size];
        }
    };
}
