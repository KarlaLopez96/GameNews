package com.karla00058615.gamenews.classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karla on 8/6/2018.
 */

public class Player implements Parcelable {

    private String name;
    private String biography;
    private String avatar;
    private String game;

    protected Player(Parcel in) {
        name = in.readString();
        biography = in.readString();
        avatar = in.readString();
        game = in.readString();
    }

    public Player(String name, String biography, String avatar, String game) {
        this.name = name;
        this.biography = biography;
        this.avatar = avatar;
        this.game = game;
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(biography);
        dest.writeString(avatar);
        dest.writeString(game);
    }
}
