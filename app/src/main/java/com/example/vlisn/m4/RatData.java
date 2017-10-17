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
import android.widget.ListView;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divya on 10/14/2017.
 */

public class RatData extends AppCompatActivity  {
    ListView ratData;
    List<String> ratList = new ArrayList<String>();
    private final Activity thisActivity = this;
    public static int args;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rat_data);
        try {
            System.out.println("reached b4 reading");
            InputStream inputStream = getResources().openRawResource(R.raw.ratsightings);
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            System.out.println("reached after reading");
            String nextLine[];
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                //System.out.println("unique key: " + nextLine[0]);
                ratList.add(nextLine[0]);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        final ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.activity_listview, ratList);
        ratData = (ListView) findViewById(R.id.ratData);
        ratData.setAdapter(adapter);
        ratData.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //Toast.makeText(getApplicationContext(), "The winner is:" + arg0.getAdapter().getItem(arg2), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(thisActivity, displayRatData.class));
                args = arg2;
            }
        });
    }
}
