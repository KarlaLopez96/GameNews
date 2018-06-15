package com.karla00058615.gamenews.data.base;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Karla on 14/6/2018.
 */

public abstract class NewsRoomDatabase extends RoomDatabase {

    public abstract NewDao newDao();

    private static NewsRoomDatabase INSTANCE;

    static NewsRoomDatabase getDatabase(final Context context){
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
