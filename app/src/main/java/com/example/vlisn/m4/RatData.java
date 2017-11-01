package com.example.vlisn.m4;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import android.view.View;
import java.util.List;

/**
 * displays the keys of the rat data
 * Created by Divya on 10/14/2017.
 */

public class RatData extends AppCompatActivity implements View.OnClickListener {
    ListView ratData;
    Button addB;
    Button mapB;
    private final Activity thisActivity = this;
    public static int args;

    /**
     * automatically read csv file & displays keys
     *
     * @param savedInstanceState bundle object used upon creation
     */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rat_data);


        addB = (Button) findViewById(R.id.add);
        mapB = (Button) findViewById(R.id.map);
        addB.setOnClickListener(this);
        mapB.setOnClickListener(this);
        //tries to read in csv file
            InputStream inputStream = getResources().openRawResource(R.raw.fiveratsightings);
            System.out.println("reached b4 reading");
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            String nextLine[];

        ratData = (ListView) findViewById(R.id.ratData);
        //when clicked, go to displayRatData activity
        ratData.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                startActivity(new Intent(thisActivity, displayRatData.class));
                args = arg2;
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                System.out.println("reached add button");
                startActivity(new Intent(this, RegisterRat.class));
            case R.id.map:
                startActivity(new Intent(this, MapsActivity.class));
        }

    }
}
