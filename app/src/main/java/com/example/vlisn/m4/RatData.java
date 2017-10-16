package com.example.vlisn.m4;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    List<String[]> ratList = new ArrayList<String[]>();

    /*public final List<String[]> readCsv(Context context) {

        AssetManager assetManager = context.getAssets();

        try {
            InputStream csvStream = assetManager.open("Rat_Sightings.csv");
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader csvReader = new CSVReader(csvStreamReader);
            String[] line;

            // throw away the header
            csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                ratList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ratList;
    }*/

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rat_data);
        try {
            System.out.println("reached b4 reading");
            InputStream inputStream = getResources().openRawResource(R.raw.fiveratsightings);
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            System.out.println("reached after reading");
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                System.out.println("DID YOU REACH HERE YET?!?!?!?!?!?!");
                // nextLine[] is an array of values from the line
                ratList.add(nextLine);
            }
        } catch(IOException e) {
            System.out.println("i got caught");
            e.printStackTrace();
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.activity_listview, ratList);
        System.out.println("reached2");
        ratData = (ListView) findViewById(R.id.ratData);
        System.out.println("reached3");
        ratData.setAdapter(adapter);
        System.out.println("reached4");
    }
}
