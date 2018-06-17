package com.karla00058615.gamenews.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.karla00058615.gamenews.R;

public class Login extends AppCompatActivity {

    Button login;
    EditText user,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        password = findViewById(R.id.password);
    }

    public void starActivity(View view){
        user = findViewById(R.id.email);
        password = findViewById(R.id.password);

        String a = user.getText().toString();
        String b = password.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user",a);
        intent.putExtra("password",b);
        startActivity(intent);
    }
}
