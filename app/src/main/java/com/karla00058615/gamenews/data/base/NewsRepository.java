package com.karla00058615.gamenews.data.base;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.karla00058615.gamenews.classes.Category;
import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.classes.Player;
import com.karla00058615.gamenews.classes.Token;
import com.karla00058615.gamenews.classes.User;
import com.karla00058615.gamenews.data.base.NewDao;
import com.karla00058615.gamenews.data.base.NewsRoomDatabase;
import com.karla00058615.gamenews.data.base.PlayerDao;
import com.karla00058615.gamenews.data.base.UserDao;

import java.util.List;

/**
 * Created by Karla on 15/6/2018.
 */

public class NewsRepository {


    private NewDao newDao;
    private PlayerDao playerDao;
    private UserDao userDao;
    private CategoryDao categoryDao;
    private TokenDao tokenDao;
    private LiveData<List<New>> news;
    private LiveData<List<Player>> players;
    private LiveData<List<User>> users;
    private LiveData<List<Category>> categorys;
    private LiveData<Token> token;

   NewsRepository (Application application){
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(application);
        newDao = db.newDao();
        playerDao = db.playerDao();
        userDao = db.userDao();
        categoryDao = db.categoryDao();
        tokenDao = db.tokenDao();

        news = newDao.getAllNews();
        players = playerDao.getAllPlayers();
        users = userDao.getAllUsers();
        categorys = categoryDao.getAllCategory();
        token = tokenDao.getToken();
    }

    LiveData<List<New>> getAllNews() {
        return news;
    }

    LiveData <Token> getToken() {
        return token;
    }

    LiveData<List<Category>> getAllCategorys() {
        return categorys;
    }

    LiveData<List<Player>> getaLLPlayers() {
        return players;
    }

    LiveData<List<User>> getAllUsers() {
        return users;
    }

    public void insertNews (New news) {
        new insertNewsAsyncTask(newDao).execute(news);
    }
    public void insertToken (Token token) {
        new insertTokenAsyncTask(tokenDao).execute(token);
    }


    public void insertCategorys (Category category) {
        new insertCategoryAsyncTask(categoryDao).execute(category);
    }

    public void insertPlayers (Player player) {
        new insertPlayerAsyncTask(playerDao).execute(player);
    }

    public void insertUsers (User user) {
        new insertUserAsyncTask(userDao).execute(user);
    }

    public void deleteALL(){
       new deleteAllAsyncTask(userDao,playerDao,categoryDao,newDao).execute();
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private NewDao newDao;
        private UserDao userDao;
        private PlayerDao playerDao;
        private CategoryDao categoryDao;

        deleteAllAsyncTask(UserDao uDao,PlayerDao pDao,CategoryDao cDao,NewDao dao) {
            newDao = dao;
            userDao = uDao;
            playerDao = pDao;
            categoryDao = cDao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            newDao.deleteAll();
            userDao.deleteAll();
            playerDao.deleteAll();
            categoryDao.deleteAll();
            return null;
        }
    }

    private static class insertTokenAsyncTask extends AsyncTask<Token, Void, Void> {

        private TokenDao tokenDao;

        insertTokenAsyncTask(TokenDao dao) {
            tokenDao = dao;
        }

        @Override
        protected Void doInBackground(final Token... params) {
            tokenDao.insert(params[0]);
            return null;
        }
    }

    private static class insertNewsAsyncTask extends AsyncTask<New, Void, Void> {

        private NewDao newDao;

        insertNewsAsyncTask(NewDao dao) {
            newDao = dao;
        }

        @Override
        protected Void doInBackground(final New... params) {
            newDao.insert(params[0]);
            return null;
        }
    }

    private static class insertCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDao categoryDao;

        insertCategoryAsyncTask(CategoryDao dao) {
            categoryDao = dao;
        }

        @Override
        protected Void doInBackground(final Category... params) {
            categoryDao.insert(params[0]);
            return null;
        }
    }

    private static class insertPlayerAsyncTask extends AsyncTask<Player, Void, Void> {

        private PlayerDao playerDao;

        insertPlayerAsyncTask(PlayerDao dao) {
            playerDao = dao;
        }

        @Override
        protected Void doInBackground(final Player... params) {
            playerDao.insert(params[0]);
            return null;
        }
    }

    private static class insertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        insertUserAsyncTask(UserDao dao) {
            userDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            userDao.insert(params[0]);
            return null;
        }
    }

}
