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

    public Token(String token) {
        this.token = token;
    }

    public Token() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
