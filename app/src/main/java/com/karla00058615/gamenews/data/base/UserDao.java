package com.karla00058615.gamenews.data.base;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.karla00058615.gamenews.classes.UserDB;

import java.util.List;

/**
 * Created by Karla on 14/6/2018.
 */

@Dao
public interface UserDao {

    @Insert
    void insert(UserDB user);

    @Query("DELETE FROM UserDB")
    void deleteAll();

    @Query("SELECT * FROM UserDB")
    LiveData<List<UserDB>> getAllUsers();
}
