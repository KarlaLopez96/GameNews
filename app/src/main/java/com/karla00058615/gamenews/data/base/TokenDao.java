package com.karla00058615.gamenews.data.base;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.karla00058615.gamenews.classes.Category;
import com.karla00058615.gamenews.classes.Token;

import java.util.List;

/**
 * Created by Karla on 15/6/2018.
 */

@Dao
public interface TokenDao {
    @Insert
    void insert(Token token);

    @Query("DELETE FROM Token")
    void deleteAll();

    @Query("SELECT * FROM Token")
    LiveData<Token> getToken();
}
