package com.example.vlisn.m4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by vlisn on 9/24/2017.
 */

public class Login extends AppCompatActivity {
    AppCompatActivity activity = Login.this;
    Button loginB, cancelB;
    EditText etUsername, etPassword;
    DatabaseHelper dbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        loginB = (Button) findViewById(R.id.loginB);
        cancelB = (Button) findViewById(R.id.cancelButton);
        dbHelper = new DatabaseHelper(activity);

    }
    public void displayWelcome(View view) {
        if (dbHelper.checkUser(etUsername.getText().toString(), etPassword.getText().toString())) {
            Intent intent = new Intent(Login.this, Welcome.class);
            startActivity(intent);
        } else { //error
            Context context = getApplicationContext();
            CharSequence text = "Incorrect username or password!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
    public void displayMain(View view) {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

  /*          public void onClick(View v) {
                switch(v.getId()) {
            case R.id.loginB:
                if((etUsername.equals("user")) && etPassword.equals("pass")) {
                    startActivity(new Intent(this, Welcome.class));
                } else {
                    //error
                    Context context = getApplicationContext();
                    CharSequence text = "Incorrect username or password!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                break;
            case R.id.cancelButton:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }*/

}
