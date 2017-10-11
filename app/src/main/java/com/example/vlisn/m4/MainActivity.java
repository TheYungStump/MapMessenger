package com.example.vlisn.m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button register;
    Button login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (Button) findViewById(R.id.registerMain);
        login = (Button) findViewById(R.id.loginMain);
    }
    public void displayLogin(View view) {
        Intent intent1 = new Intent(MainActivity.this, Login.class);
        startActivity(intent1);
    }
    public void displayRegistration(View view) {
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }
}

