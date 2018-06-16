package com.karla00058615.gamenews.data.base;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.classes.Player;
import com.karla00058615.gamenews.classes.Token;
import com.karla00058615.gamenews.classes.User;

/**
 * Created by Karla on 14/6/2018.
 */

@Database(entities = {New.class, Player.class, User.class,CategoryDao.class, Token.class}, version = 1)
public abstract class NewsRoomDatabase extends RoomDatabase {

    public abstract NewDao newDao();
    public abstract PlayerDao playerDao();
    public abstract UserDao userDao();
    public abstract CategoryDao categoryDao();
    public abstract TokenDao tokenDao();

    private static NewsRoomDatabase INSTANCE;

    public static NewsRoomDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (NewsRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        NewsRoomDatabase.class, "news_database")
                        .build();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
