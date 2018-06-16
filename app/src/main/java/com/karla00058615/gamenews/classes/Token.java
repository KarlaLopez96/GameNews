package com.karla00058615.gamenews.classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Karla on 4/6/2018.
 */

@Entity(tableName = "Token")
public class Token {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "token")
    private String token;

    @ColumnInfo(name = "UserId")
    private String id;

    public Token() {
    }

    public Token(@NonNull String token, String id) {
        this.token = token;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
