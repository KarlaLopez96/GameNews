package com.karla00058615.gamenews.data.base;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.karla00058615.gamenews.classes.User;

import java.util.List;

/**
 * Created by Karla on 14/6/2018.
 */

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("DELETE FROM User")
    void deleteAll();

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();
}
