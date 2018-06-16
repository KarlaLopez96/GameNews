package com.karla00058615.gamenews.data.base;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.karla00058615.gamenews.classes.Category;

import java.util.List;

/**
 * Created by Karla on 15/6/2018.
 */

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Query("DELETE FROM Category")
    void deleteAll();

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getAllCategory();

}
