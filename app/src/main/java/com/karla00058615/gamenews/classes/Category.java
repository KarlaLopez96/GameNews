package com.karla00058615.gamenews.classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Karla on 15/6/2018.
 */

@Entity(tableName = "Category")
public class Category {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "category")
    private String category;

    public Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
