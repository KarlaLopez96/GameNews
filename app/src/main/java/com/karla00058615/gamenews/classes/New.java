package com.karla00058615.gamenews.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karla on 3/6/2018.
 */

public class New implements Parcelable{
    private String _id;
    private String title;
    private String body;
    private String game;
    private String convertImg;
    private String description;
    private String created_date;

    public New(String _id, String title, String body, String game, String convertImg, String description, String created_date) {
        this._id = _id;
        this.title = title;
        this.body = body;
        this.game = game;
        this.convertImg = convertImg;
        this.description = description;
        this.created_date = created_date;
    }

    protected New(Parcel in) {
        _id = in.readString();
        title = in.readString();
        body = in.readString();
        game = in.readString();
        convertImg = in.readString();
        description = in.readString();
        created_date = in.readString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
        dest.writeString(_id);
        dest.writeString(title);
        dest.writeString(body);
        dest.writeString(game);
        dest.writeString(convertImg);
        dest.writeString(description);
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
