package com.karla00058615.gamenews.data.base;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.karla00058615.gamenews.classes.FavNewDB;
import com.karla00058615.gamenews.classes.New;

import java.util.List;

/**
 * Created by Karla on 16/6/2018.
 */

@Dao
public interface FavNewDAO {
    @Insert
    void insert(FavNewDB news);

    @Query("DELETE FROM Fav")
    void deleteAll();

    @Query("SELECT * FROM Fav")
    LiveData<List<FavNewDB>> getAllNews();
}
