package com.example.vlisn.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Divya on 10/25/2017.
 */

public class RatRegister extends AppCompatActivity implements View.OnClickListener{

    Button addB;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rat_register);

        addB = (Button) findViewById(R.id.add);
        addB.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                System.out.println("reached add button");
                startActivity(new Intent(this, RatRegister.class));
        }

    }


}
