package com.karla00058615.gamenews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karla00058615.gamenews.R;
import com.karla00058615.gamenews.classes.Token;
import com.karla00058615.gamenews.classes.TokenDeserializar;
import com.karla00058615.gamenews.classes.User;
import com.karla00058615.gamenews.interfaces.NoticiasAPI;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {

    EditText pass,confirmPass;
    Button button;
    String token,id;

    public static Settings newInstance(String token,String userId) {
        Settings fragmentFirst = new Settings();
        Bundle args = new Bundle();
        args.putString("Token", token);
        args.putString("id",userId);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        button = v.findViewById(R.id.confirm);
        pass = v.findViewById(R.id.newPass);
        confirmPass = v.findViewById(R.id.conFirmPass);
        token = getArguments().getString("Token");
        id = getArguments().getString("id");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getText().toString().equals(confirmPass.getText().toString())){
                    Gson gson = new GsonBuilder()
                            .registerTypeAdapter(Token.class,new TokenDeserializar())
                            .create();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://gamenewsuca.herokuapp.com/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

                    NoticiasAPI servicio = retrofit.create(NoticiasAPI.class);

                    Call<User> call = servicio.updateUser ("Bearer "+token,id,pass.getText().toString());
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            message();
                        }
                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            String p ;
                        }
                    });
                }
            }
        });


        return v;
    }

    public void message(){
        Toast.makeText(getContext(),"Contraseña guardada",Toast.LENGTH_SHORT);
    }
}
