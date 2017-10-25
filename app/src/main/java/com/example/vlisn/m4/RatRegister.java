package com.example.vlisn.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Divya on 10/25/2017.
 */

public class RatRegister extends AppCompatActivity implements View.OnClickListener{

    Button addB;
    EditText borough= (EditText) findViewById(R.id.borough);
    String boroughVal = borough.getText().toString();
    EditText city = (EditText) findViewById(R.id.city);
    String cityVal = city.getText().toString();
    EditText address= (EditText) findViewById(R.id.incidentAddress);
    String addressVal = address.getText().toString();
    EditText zip= (EditText) findViewById(R.id.incidentZip);
    String zipVal = zip.getText().toString();
    EditText location= (EditText) findViewById(R.id.locationType);
    String locationVal = location.getText().toString();
    EditText createData = (EditText) findViewById(R.id.createData);
    String createDataVal = createData.getText().toString();
    EditText latitude= (EditText) findViewById(R.id.latitude);
    String latitudeVal = latitude.getText().toString();
    EditText longitude= (EditText) findViewById(R.id.longitude);
    String longitudeVal = longitude.getText().toString();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rat_register);

        addB = (Button) findViewById(R.id.add);
        addB.setOnClickListener(this);
        System.out.println("HI");
    }

    public void onClick(View v) {
        System.out.println("reached add");
        switch (v.getId()) {
            case R.id.add:
                try {
                    System.out.println("reached add button");
                    File file = new File("addedRats.csv");
                    // if file doesnt exists, then create it
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(createDataVal);
                    bw.write(locationVal);
                    bw.write(zipVal);
                    bw.write(addressVal);
                    bw.write(cityVal);
                    bw.write(boroughVal);
                    bw.write(latitudeVal);
                    bw.write(longitudeVal);
                    bw.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(this, RatRegister.class));
        }

    }


}
