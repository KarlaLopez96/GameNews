package com.karla00058615.gamenews.classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Karla on 16/6/2018.
 */

@Entity(tableName = "Fav")
public class FavNewDB {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idNew")
    private String idNew;

    public FavNewDB(String idNew) {
        this.idNew = idNew;
    }

    public String getIdNew() {
        return idNew;
    }

    public void setIdNew(String idNew) {
        this.idNew = idNew;
    }
}
