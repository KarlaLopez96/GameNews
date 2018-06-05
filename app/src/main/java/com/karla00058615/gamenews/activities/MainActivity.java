package com.karla00058615.gamenews.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.interfaces.NoticiasAPI;
import com.karla00058615.gamenews.R;
import com.karla00058615.gamenews.classes.Token;
import com.karla00058615.gamenews.classes.TokenDeserializar;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String token="null";
    NoticiasAPI servicio;
    ArrayList<New> news;

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
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
    }

    public void getNews (View view){
        Call<List<New>> call = servicio.getNews("Bearer "+token);
        call.enqueue(new Callback<List<New>>() {
            @Override
            public void onResponse(Call<List<New>> call, Response<List<New>> response) {
                TextView text = findViewById(R.id.textoPrueba);
                text.setText(response.body().get(5).getDescription());
                for (int i = 0 ; i<response.body().size();i++){
                    news.add(response.body().get(i));
                }
            }

            @Override
            public void onFailure(Call<List<New>> call, Throwable t) {
                String HOLI;
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_slideshow) {
            // Handle the camera action{ {
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
