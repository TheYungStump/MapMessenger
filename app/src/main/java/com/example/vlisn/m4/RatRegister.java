package com.example.vlisn.m4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * used for inputting rat data
 * Created by Divya on 10/25/2017.
 */

public class RatRegister extends AppCompatActivity implements View.OnClickListener{

    Button addB, cancelB ;

    EditText borough, city, address, zip, location, createData, latitude, longitude;
    String boroughVal, cityVal, addressVal, zipVal, locationVal, createDataVal, latitudeVal, longitudeVal;
    Random rand;
    ArrayList<String> newRatList = new ArrayList<String>();
    ArrayList<String> newRatList2 = new ArrayList<String>();

    public static ArrayList<String> ratList = new ArrayList<String>();

    /**
     * sets up the RatRegister class
     * @param savedInstanceState used upon creation of class
     */

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rat_register);

        borough= (EditText) findViewById(R.id.borough);
        city = (EditText) findViewById(R.id.city);
        address = (EditText) findViewById(R.id.incidentAddress);
        zip= (EditText) findViewById(R.id.incidentZip);
        location= (EditText) findViewById(R.id.locationType);
        createData = (EditText) findViewById(R.id.createData);
        latitude= (EditText) findViewById(R.id.latitude);
        longitude= (EditText) findViewById(R.id.longitude);



        addB = (Button) findViewById(R.id.addrat);
        cancelB = (Button) findViewById(R.id.cancelrat);
        addB.setOnClickListener(this);
        cancelB.setOnClickListener(this);
    }

    /**
     * added onClick to determine what should happen when a button is clicked
     * @param v View to get id of button
     */

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.addrat:
                createDataVal = createData.getText().toString();
                boroughVal = borough.getText().toString().trim();
                cityVal = city.getText().toString();
                addressVal = address.getText().toString();
                zipVal = zip.getText().toString();
                locationVal = location.getText().toString();
                latitudeVal = latitude.getText().toString();
                longitudeVal = longitude.getText().toString();

                rand = new Random();
                int key = rand.nextInt((40000000 - 30000000) + 1) + 30000000;
                String value = Integer.toString(key);

                ratList.add(value);
                ratList.add(createDataVal);
                ratList.add(locationVal);
                ratList.add(zipVal);
                ratList.add(addressVal);
                ratList.add(cityVal);
                ratList.add(boroughVal);
                ratList.add(latitudeVal);
                ratList.add(longitudeVal);

                for (int k = ratList.size() - 1; k >= (ratList.size() - 9); k--) {
                    System.out.println("getVals" + ratList.get(k));
                    newRatList.add(ratList.get(k));
                }
                int j = newRatList.size() - 1;
                for (int i = 0; i < 9 ; i++) {
                    newRatList2.add(newRatList.get(j));
                    j--;
                }
                startActivity(new Intent(this, RatData.class));

            case R.id.cancelrat:
                startActivity(new Intent(this, RatData.class));
        }

    }


}
