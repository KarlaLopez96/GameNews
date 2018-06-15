package com.karla00058615.gamenews.data.base;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.karla00058615.gamenews.classes.Player;

import java.util.List;

/**
 * Created by Karla on 14/6/2018.
 */

@Dao
public interface PlayerDao {

    @Insert
    void insert(Player player);

    @Query("DELETE FROM Player")
    void deleteAll();

    @Query("SELECT * FROM Player")
    List<Player> getAllPlayers();
}
