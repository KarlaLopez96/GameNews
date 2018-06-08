package com.karla00058615.gamenews.activities;

import android.net.Uri;
import android.os.Bundle;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.classes.Player;
import com.karla00058615.gamenews.fragments.ManagerFragment;
import com.karla00058615.gamenews.fragments.NewsList;
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
        implements NavigationView.OnNavigationItemSelectedListener,ManagerFragment.OnFragmentInteractionListener{

    int cont = 0;
    String token="null";
    NoticiasAPI servicio;
    ArrayList<New> news = new ArrayList<>();
    ArrayList<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
                    news.add(response.body().get(i));
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
                String p;
                for (int i = 0 ; i<response.body().size();i++){
                    players.add(response.body().get(i));
                }
            }
            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                String p ;
            }
        });
    }

    @Override
    public void onBackPressed() {
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_news) {
            sendingAll(0);
        } else if (id == R.id.nav_Games) {
            sendingAll(1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
            case 0:
                NewsList fragment2 = NewsList.newInstance(news);

                transaction.replace(R.id.Fragment, fragment2);

                //Realizando cambios.
                transaction.commit();
                break;
            case 1:
                for (int i = 0;i<news.size();i++){
                    if(news.get(i).getGame().equals("lol"))
                        gamesN.add(news.get(i));
                }

                for (int i = 0;i<players.size();i++){
                    if(players.get(i).getGame().equals("lol"))
                        top.add(players.get(i));
                }

                bundle.putParcelableArrayList("News",gamesN);
                bundle.putParcelableArrayList("Top",top);

                //se manda el bundle al fragment
                fragment.setArguments(bundle);

                transaction.replace(R.id.Fragment, fragment);

                //Realizando cambios.
                transaction.commit();
                break;

            case 2:
                for (int i = 0;i<news.size();i++){
                    if(news.get(i).getGame().equals("csgo"))
                        gamesN.add(news.get(i));
                }

                for (int i = 0;i<players.size();i++){
                    if(players.get(i).getGame().equals("csgo"))
                        top.add(players.get(i));
                }

                bundle.putParcelableArrayList("News",gamesN);
                bundle.putParcelableArrayList("Top",top);

                //se manda el bundle al fragment
                fragment.setArguments(bundle);

                transaction.replace(R.id.Fragment, fragment);

                //Realizando cambios.
                transaction.commit();
                break;
            case 3:
                for (int i = 0;i<news.size();i++){
                    if(news.get(i).getGame().equals("dota"))
                        gamesN.add(news.get(i));
                }

                for (int i = 0;i<players.size();i++){
                    if(players.get(i).getGame().equals("dota"))
                        top.add(players.get(i));
                }

                bundle.putParcelableArrayList("News",gamesN);
                bundle.putParcelableArrayList("Top",top);
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
}
