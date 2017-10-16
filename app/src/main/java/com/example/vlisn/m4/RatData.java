package com.example.vlisn.m4;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Divya on 10/14/2017.
 */

public class RatData extends AppCompatActivity  {
    ListView ratData;
    String[] arrayTest = {"Lisna","Vargs","Divya","Yags",
            "Stanely","Mason","Buch","ourboi", "vishaaaaaal", "Rajat", "PROM?!?!?!?!?!??!"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rat_data);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, arrayTest);
        System.out.println("reached2");
        ratData = (ListView) findViewById(R.id.ratData);
        System.out.println("reached3");
        ratData.setAdapter(adapter);
        System.out.println("reached4");
    }
}
