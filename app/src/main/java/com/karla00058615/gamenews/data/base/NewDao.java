package com.karla00058615.gamenews.data.base;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.karla00058615.gamenews.classes.New;

import java.util.List;

/**
 * Created by Karla on 14/6/2018.
 */

@Dao
public interface NewDao {

    @Insert
    void insert(New news);

    @Query("DELETE FROM News")
    void deleteAll();

    @Query("SELECT * FROM News ORDER BY created_date DESC")
    LiveData<List<New>> getAllNews();
}
