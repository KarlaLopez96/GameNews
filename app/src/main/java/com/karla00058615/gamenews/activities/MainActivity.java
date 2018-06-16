package com.karla00058615.gamenews.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karla00058615.gamenews.classes.Category;
import com.karla00058615.gamenews.classes.FavNewDB;
import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.classes.NewFav;
import com.karla00058615.gamenews.classes.Player;
import com.karla00058615.gamenews.classes.User;
import com.karla00058615.gamenews.data.base.NewsViewModel;
import com.karla00058615.gamenews.fragments.ManagerFragment;
import com.karla00058615.gamenews.fragments.NewsList;
import com.karla00058615.gamenews.interfaces.ComunicationIF;
import com.karla00058615.gamenews.interfaces.NoticiasAPI;
import com.karla00058615.gamenews.R;
import com.karla00058615.gamenews.classes.Token;
import com.karla00058615.gamenews.classes.TokenDeserializar;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ManagerFragment.OnFragmentInteractionListener,ComunicationIF{

    private int cont = 0;
    private String token="null";
    private NoticiasAPI servicio;
    private String userId;
    private ArrayList<New> news = new ArrayList<>();
    private ArrayList<New> favorits = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<String> category = new ArrayList<>();
    private NewsViewModel newsViewModel;
    private boolean flag = true;
    SubMenu subMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();

        subMenu = menu.addSubMenu(0,1,1,"Games");


        newsViewModel.getAllNews().observe(this, new Observer<List<New>>() {
            @Override
            public void onChanged(@Nullable List<New> n) {
                news.clear();
                for (int i = 0;i<n.size();i++){
                    news.add(n.get(i));
                }
            }
        });

        newsViewModel.getAllPlayer().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable List<Player> p) {
                players.clear();
                for (int i = 0;i<p.size();i++){
                    players.add(p.get(i));
                }
            }
        });
        newsViewModel.getAllCategory().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> c) {
                category.clear();
                for (int i = 0;i<c.size();i++){
                    category.add(c.get(i).getCategory());
                }
                addMenuItemInNavMenuDrawer();
            }
        });
        newsViewModel.getAllFavNews().observe(this, new Observer<List<FavNewDB>>() {
            @Override
            public void onChanged(@Nullable List<FavNewDB> favNewDBS) {
                favorits.clear();
                for(int h = 0 ; h<favNewDBS.size(); h++){
                    for (int y = 0;y<news.size();y++){
                        if (news.get(y).get_id().equals(favNewDBS.get(h).getIdNew())){
                            favorits.add(news.get(y));
                        }
                    }
                }
            }
        });

        //addMenuItemInNavMenuDrawer();
        //Estaba itentando sacar la lista de noticias favoritas del ususrario actual
        //falta que ver bien como se esta seteando el arreglo de noticias favoritas en la base
        //recordando que se guardan los ids de las noticias favoritas
    }

    public void update(){
        newsViewModel.deleteAll();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Token.class,new TokenDeserializar())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gamenewsuca.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        servicio = retrofit.create(NoticiasAPI.class);
        Call<Token> call = servicio.getToken("00058615","00058615");
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Log.d("Token",response.body().getToken());
                token = response.body().getToken();
                newsViewModel.insertToken(new Token(token));
                getList();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
    }

    public void getList(){
        Call<List<New>> call = servicio.getNews("Bearer "+token);
        call.enqueue(new Callback<List<New>>() {
            @Override
            public void onResponse(Call<List<New>> call, Response<List<New>> response) {
                for (int i = 0 ; i<response.body().size();i++){
                    newsViewModel.insertNews(response.body().get(i));
                }
                getPlayers();
            }
            @Override
            public void onFailure(Call<List<New>> call, Throwable t) {
            }
        });
    }

    public void getPlayers(){
        Log.d("Token",token);
        Call<List<Player>> call = servicio.getPlayers ("Bearer "+token);
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                for (int i = 0 ; i<response.body().size();i++){
                    newsViewModel.insertPlayers(response.body().get(i));
                }
                getCategory();
            }
            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                String p ;
            }
        });
    }

    public void getCategory (){
        Log.d("Token",token);
        Call<List<String>> call = servicio.getCategory ("Bearer "+token);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                for (int i = 0 ; i<response.body().size();i++){
                    newsViewModel.insertCategorys(new Category(response.body().get(i)));
                }
                getUser();
                getUserListt();
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
            }
        });
    }

    public void getUserListt (){
        Log.d("Token",token);
        Call<List<User>> call = servicio.getUsersList ("Bearer "+token);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                for (int i = 0 ; i<response.body().size();i++){
                    newsViewModel.insertUsers(response.body().get(i));
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }

    // estabas haciendo el post del agregar favoritos
    public void addFav(String id) {
        newsViewModel.insertFavNews(id);
        if(Internet()){
            Call<NewFav> call = servicio.addFav("Bearer " + token,userId,id);
            call.enqueue(new Callback<NewFav>() {
                @Override
                public void onResponse(Call<NewFav> call, Response<NewFav> response) {
                    if(response.body().getSuccess().equals("true")){
                        String p;
                    }
                }
                @Override
                public void onFailure(Call<NewFav> call, Throwable t) {
                }
            });
        }
    }

    public void removeFav(String id) {
        newsViewModel.DeleteAFavNew(id);
        if(Internet()){
            Call<String> call = servicio.removeFav("Bearer " + token,userId,id);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.body().equals("The New has been Removed")){
                        System.out.println("removido como se debe");
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                }
            });
        }
    }

    public void getUser(){
        Call<User> call = servicio.getUserInfo ("Bearer "+token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userId = response.body().get_id();
                for (int i = 0;i<response.body().getFavoriteNews().size();i++){
                    newsViewModel.insertFavNews(response.body().getFavoriteNews().get(i));
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private void addMenuItemInNavMenuDrawer() {
        subMenu.clear();
        for (int i = 0;i<category.size();i++){
            subMenu.add(0,i,1,category.get(i));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }
        if (id == R.id.refresh){
            update();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_news) {
            sendingAll(R.id.nav_news);
        }
        if (id == R.id.nav_fav){
            sendingAll(R.id.nav_fav);
        } else{
            for (int i = 0 ; i<category.size();i++){
                if(id == i){
                    sendingAll(i);
                }
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean Internet(){
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public void sendingAll(int opc){
        ArrayList<New> gamesN = new ArrayList<>();
        ArrayList<Player> top = new ArrayList<>();

        //Maneja los fragmentos.
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Crea una nueva trasacción.
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //Crea un fragmento y lo añade.
        ManagerFragment fragment = new ManagerFragment();

        //se crea el bundle y se mandan todas las contactos
        Bundle bundle = new Bundle();
        switch (opc){
            case R.id.nav_news:
                NewsList fragment2 = NewsList.newInstance(news,favorits);

                transaction.replace(R.id.Fragment, fragment2);

                //Realizando cambios.
                transaction.commit();
                break;
            case R.id.nav_fav:
                NewsList fragment3 = NewsList.newInstance(favorits,favorits);

                transaction.replace(R.id.Fragment, fragment3);

                //Realizando cambios.
                transaction.commit();
                break;
            default:
                for (int i = 0;i<news.size();i++){
                    if(news.get(i).getGame().equals(category.get(opc)))
                        gamesN.add(news.get(i));
                }

                for (int i = 0;i<players.size();i++){
                    if(players.get(i).getGame().equals(category.get(opc)))
                        top.add(players.get(i));
                }

                bundle.putParcelableArrayList("News",gamesN);
                bundle.putParcelableArrayList("Top",top);
                bundle.putParcelableArrayList("Favorits",favorits);

                //se manda el bundle al fragment
                fragment.setArguments(bundle);

                transaction.replace(R.id.Fragment, fragment);

                //Realizando cambios.
                transaction.commit();
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void addfav(New noticia) {
        addFav(noticia.get_id());
        favorits.add(noticia);
    }

    @Override
    public void remove(New noticia) {
        removeFav(noticia.get_id());
        favorits.remove(noticia);
    }
}
