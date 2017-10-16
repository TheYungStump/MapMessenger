package com.example.vlisn.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by vlisn on 9/24/2017.
 */

public class Welcome extends AppCompatActivity implements View.OnClickListener {
    Button logoutB;
    Button rat_data;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        logoutB = (Button) findViewById(R.id.logout);
        rat_data = (Button) findViewById(R.id.rat_data);

        logoutB.setOnClickListener(this);
        rat_data.setOnClickListener(this);

    }

    public void onClick(View v){
        switch(v.getId()) {
            case R.id.logout:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.rat_data:
                System.out.println("");
                startActivity(new Intent(this, RatData.class));
                break;
        }
    }
}