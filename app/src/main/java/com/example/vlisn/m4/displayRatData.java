package com.example.vlisn.m4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divya on 10/17/2017.
 */

public class displayRatData extends AppCompatActivity{

    int args = RatData.args;
    ListView ratData;
    List<String> ratList = new ArrayList<String>();

    /**
     * displays information about each key upon clicking on it
     * @param savedInstanceState bundle object that is passed on creation
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_rat_data);
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.ratsightings);
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            String nextLine[];
            String nextNextLine[];
            System.out.println("Count: " + args);
            nextLine = reader.readNext();
            nextNextLine = nextLine;
            for (int i = 0; i < args; i ++) {
                nextNextLine = reader.readNext();
            }
            //displays the required data
            if (nextNextLine[1] != null ) {
                ratList.add(nextLine[1] + ": " + nextNextLine[1]);
            } else {
                ratList.add(nextLine[1] + ": " + " ");
            }
            if (nextNextLine[7] != null ) {
                ratList.add(nextLine[7] + ": " + nextNextLine[7]);
            } else {
                ratList.add(nextLine[7] + ": " + " ");
            }
            if (nextNextLine[8] != null ) {
                ratList.add(nextLine[8] + ": " + nextNextLine[8]);
            } else {
                ratList.add(nextLine[8] + ": " + " ");
            }
            if (nextNextLine[9] != null ) {
                ratList.add(nextLine[9] + ": " + nextNextLine[9]);
            } else {
                ratList.add(nextLine[9] + ": " + " ");
            }
            if (nextNextLine[16] != null ) {
                ratList.add(nextLine[16] + ": " + nextNextLine[16]);
            } else {
                ratList.add(nextLine[16] + ": " + " ");
            }
            if (nextNextLine[23] != null ) {
                ratList.add(nextLine[23] + ": " + nextNextLine[23]);
            } else {
                ratList.add(nextLine[23] + ": " + " ");
            }
            if (nextNextLine[24] != null ) {
                ratList.add(nextLine[24] + ": " + nextNextLine[24]);
            } else {
                ratList.add(nextLine[24] + ": " + " ");
            }
            if (nextNextLine[25] != null ) {
                ratList.add(nextLine[25] + ": " + nextNextLine[25]);
            } else {
                ratList.add(nextLine[25] + ": " + " ");
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        final ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.activity_listview, ratList);
        ratData = (ListView) findViewById(R.id.ratDisplay);
        ratData.setAdapter(adapter);
    }
}
