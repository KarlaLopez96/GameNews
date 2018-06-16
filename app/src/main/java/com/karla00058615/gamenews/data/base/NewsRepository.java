package com.karla00058615.gamenews.data.base;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.karla00058615.gamenews.classes.Category;
import com.karla00058615.gamenews.classes.FavNewDB;
import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.classes.Player;
import com.karla00058615.gamenews.classes.Token;
import com.karla00058615.gamenews.classes.UserDB;

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
    private FavNewDAO favNewDAO;

    private LiveData<List<New>> news;
    private LiveData<List<Player>> players;
    private LiveData<List<UserDB>> users;
    private LiveData<List<Category>> categorys;
    private LiveData<Token> token;
    private LiveData<List<FavNewDB>> favNew;

   NewsRepository (Application application){
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(application);
        newDao = db.newDao();
        playerDao = db.playerDao();
        userDao = db.userDao();
        categoryDao = db.categoryDao();
        tokenDao = db.tokenDao();
        favNewDAO = db.favNewDAO();

        news = newDao.getAllNews();
        players = playerDao.getAllPlayers();
        users = userDao.getAllUsers();
        categorys = categoryDao.getAllCategory();
        token = tokenDao.getToken();
        favNew = favNewDAO.getAllNews();
    }

    LiveData<List<New>> getAllNews() {
        return news;
    }

    LiveData<List<FavNewDB>> getAllFav() {
        return favNew;
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

    LiveData<List<UserDB>> getAllUsers() {
        return users;
    }

    public void insertNews (New news) {
        new insertNewsAsyncTask(newDao).execute(news);
    }

    public void insertFavs (FavNewDB favNewDB) {
        new insertFavNewsAsyncTask(favNewDAO).execute(favNewDB);
    }

    public void deleteFav (String favNewDB) {
        new deleteaFavNewsAsyncTask(favNewDAO).execute(favNewDB);
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

    public void insertUsers (UserDB userDB) {
        new insertUserAsyncTask(userDao).execute(userDB);
    }

    public void deleteALL(){
       new deleteAllAsyncTask(userDao,favNewDAO,playerDao,categoryDao,newDao,tokenDao).execute();
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private NewDao newDao;
        private FavNewDAO favNewDAO;
        private UserDao userDao;
        private PlayerDao playerDao;
        private CategoryDao categoryDao;
        private TokenDao tokenDao;

        deleteAllAsyncTask(UserDao uDao,FavNewDAO favNewDAO,PlayerDao pDao,CategoryDao cDao,NewDao dao,TokenDao tokenDao) {
            this.newDao = dao;
            this.favNewDAO = favNewDAO;
            this.userDao = uDao;
            this.playerDao = pDao;
            this.categoryDao = cDao;
            this.tokenDao = tokenDao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            newDao.deleteAll();
            userDao.deleteAll();
            playerDao.deleteAll();
            categoryDao.deleteAll();
            favNewDAO.deleteAll();
            tokenDao.deleteAll();
            return null;
        }
    }

    private static class deleteaFavNewsAsyncTask extends AsyncTask<String, Void, Void> {

        private FavNewDAO favNewDAO;

        deleteaFavNewsAsyncTask(FavNewDAO dao) {
            favNewDAO = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            favNewDAO.deleteFav(params[0]);
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

    private static class insertFavNewsAsyncTask extends AsyncTask<FavNewDB, Void, Void> {

        private FavNewDAO favNewDAO;

        insertFavNewsAsyncTask(FavNewDAO dao) {
            favNewDAO = dao;
        }

        @Override
        protected Void doInBackground(final FavNewDB... params) {
            favNewDAO.insert(params[0]);
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

    private static class insertUserAsyncTask extends AsyncTask<UserDB, Void, Void> {

        private UserDao userDao;

        insertUserAsyncTask(UserDao dao) {
            userDao = dao;
        }

        @Override
        protected Void doInBackground(final UserDB... params) {
            userDao.insert(params[0]);
            return null;
        }
    }

}
