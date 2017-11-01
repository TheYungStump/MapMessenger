package com.example.vlisn.m4;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.google.firebase.database.ValueEventListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * displays the keys of the rat data
 * Created by Divya on 10/14/2017.
 */

public class RatData extends AppCompatActivity implements View.OnClickListener {
    Button addB;
    Button mapB;
    private final Activity thisActivity = this;
    public static int args;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    ArrayAdapter<String> adapter;

    /**
     * automatically read csv file & displays keys
     *
     * @param savedInstanceState bundle object used upon creation
     */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rat_data);
        System.out.println("reach here");

        addB = (Button) findViewById(R.id.add);
        mapB = (Button) findViewById(R.id.map);
        addB.setOnClickListener(this);
        mapB.setOnClickListener(this);
        final ListView ratData = (ListView) findViewById(R.id.ratData);
        final ArrayList<String> ratDatalist = new ArrayList<String>();
        DatabaseReference mFirebaseInstance1 = FirebaseDatabase.getInstance().getReference().child("rats");
        mFirebaseInstance1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String ratkeysName = (String) ds.getKey();
                        ratDatalist.add(ratkeysName);
                    }
                    adapter = new ArrayAdapter<String>(RatData.this,
                            R.layout.activity_listview, ratDatalist);
                    ratData.setAdapter(adapter);

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    throw databaseError.toException(); // don't ignore errors
                }
            });
        //when clicked, go to displayRatData activity
        ratData.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
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





