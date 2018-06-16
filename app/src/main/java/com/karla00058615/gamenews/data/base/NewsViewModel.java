package com.karla00058615.gamenews.data.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.karla00058615.gamenews.classes.Category;
import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.classes.Player;
import com.karla00058615.gamenews.classes.Token;
import com.karla00058615.gamenews.classes.User;

import java.util.List;

/**
 * Created by Karla on 15/6/2018.
 */

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository newsRepository;
    private LiveData<List<New>> allNews;
    private LiveData<List<Player>> allPlayer;
    private LiveData<List<User>> allUser;
    private LiveData<List<Category>> allCategory;
    private LiveData <Token> token;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
        allNews = newsRepository.getAllNews();
        allPlayer = newsRepository.getaLLPlayers();
        allUser = newsRepository.getAllUsers();
        allCategory = newsRepository.getAllCategorys();
        token = newsRepository.getToken();
    }

    public LiveData<List<New>> getAllNews() {
        return allNews;
    }

    public LiveData<Token> getToken() {
        return token;
    }

    public LiveData<List<Player>> getAllPlayer() {
        return allPlayer;
    }

    public LiveData<List<User>> getAllUser() {
        return allUser;
    }

    public LiveData<List<Category>> getAllCategory(){
        return allCategory;
    }

    public void insertNews(New news) { newsRepository.insertNews(news); }

    public void insertToken(Token token) { newsRepository.insertToken(token); }


    public void insertCategorys(Category category){
        newsRepository.insertCategorys(category);
    }

    public void insertPlayers(Player player) { newsRepository.insertPlayers(player); }

    public void insertUsers(User user) { newsRepository.insertUsers(user); }

    public void deleteAll(){
        newsRepository.deleteALL();
    }
}
