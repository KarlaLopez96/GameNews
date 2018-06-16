package com.karla00058615.gamenews.classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by Karla on 8/6/2018.
 */

@Entity (tableName = "Player")
public class Player implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "id")
    private String _id;

    @ColumnInfo (name = "name")
    private String name;

    @ColumnInfo(name = "biography")
    private String biografia;

    @ColumnInfo (name = "avatar")
    private String avatar;

    @ColumnInfo(name = "game")
    private String game;

    protected Player(Parcel in) {
        name = in.readString();
        biografia = in.readString();
        avatar = in.readString();
        game = in.readString();
    }

    public Player(String id,String name, String biografia, String avatar, String game) {
        this._id = id;
        this.name = name;
        this.biografia = biografia;
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

    @NonNull
    public String get_id() {
        return _id;
    }

    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biografia;
    }

    public void setBiography(String biografia) {
        this.biografia = biografia;
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
        dest.writeString(biografia);
        dest.writeString(avatar);
        dest.writeString(game);
    }
}
