package com.example.vlisn.m4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Random;

public class RegisterRat extends AppCompatActivity{
    Button ratCreate, ratCancel;
    EditText createdDate, locationType, incidentZip, incidentAddress, city,borough,latitude, longitude;
    FirebaseAuth mAuth;
    int args = RatData.args;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    public String ratId;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rat_register);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        createdDate = (EditText) findViewById(R.id.createData);
        locationType = (EditText) findViewById(R.id.locationType);
        incidentZip = (EditText)  findViewById(R.id.incidentZip);
        incidentAddress = (EditText) findViewById(R.id.incidentAddress);
        city = (EditText) findViewById(R.id.city);
        borough = (EditText) findViewById(R.id.borough);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        ratCreate = (Button) findViewById(R.id.addrat);
        ratCancel = (Button) findViewById(R.id.cancelrat);
        ratId= "";

        mAuth = FirebaseAuth.getInstance();


        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("rats");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("m5");

        // app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("TAG", "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("TAG", "Failed to read app title value.", error.toException());
            }
        });

        // Save / update the user
        ratCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uniquekey = "";
                String realcreatedDate = createdDate.getText().toString().trim();
                String realLocationType= locationType.getText().toString().trim();
                String realincidentZip= incidentZip.getText().toString().trim();
                String realincidentAddress = incidentAddress.getText().toString().trim();
                String realcity= city.getText().toString().trim();
                String realborough = borough.getText().toString().trim();
                String reallatitude = latitude.getText().toString().trim();
                String reallongitude= longitude.getText().toString().trim();
                System.out.print("errrroeoeeer1");

                if (realcreatedDate.equals("") || realincidentZip.equals("") || realLocationType.equals("")
                        || realincidentZip.equals("") || realincidentAddress.equals("") || realcity.equals("")
                        || realborough.equals("") || reallatitude.equals("") || reallongitude.equals("")) {
                    Context context = getApplicationContext();
                    CharSequence text = "Enter your all contents!";
                    System.out.print("errrroeoeeer3");
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                } else {
                    System.out.println("errrroeoeeerq4");
                    try {
                        InputStream inputStream = getResources().openRawResource(R.raw.fiveratsightings);
                        CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
                        String nextLine[];
                        String nextNextLine[];
                        System.out.println("Count: " + args);
                        nextLine = reader.readNext();
                        nextNextLine = nextLine;
                        String line ="";
                        System.out.println(nextNextLine);
                      //for (int i = 0; i < 100; i++) {
                          nextNextLine = reader.readNext();
                          //displays the required data
                            if (nextNextLine[0] != null) {
                                uniquekey = nextNextLine[0];
                            } else {
                                uniquekey = " ";
                            }
                            if (nextNextLine[1] != null) {
                                realcreatedDate = nextNextLine[1];
                            } else {
                                realcreatedDate = " ";
                            }
                            if (nextNextLine[7] != null) {
                                realLocationType = nextNextLine[7];
                            } else {
                                realLocationType = " ";
                            }
                            if (nextNextLine[8] != null) {
                                realincidentAddress = nextNextLine[8];
                            } else {
                                realincidentAddress = " ";
                            }
                            if (nextNextLine[9] != null) {
                                realincidentZip = nextNextLine[9];
                            } else {
                                realincidentZip = " ";
                            }
                            if (nextNextLine[16] != null) {
                                realcity = nextNextLine[16];
                            } else {
                                realcity = " ";
                            }
                            if (nextNextLine[23] != null) {
                                realborough = nextNextLine[23];
                            } else {
                                realborough = " ";
                            }
                            if (nextNextLine[49] != null) {
                                reallatitude = nextNextLine[49];
                            } else {
                                reallatitude = " ";
                            }
                            if (nextNextLine[50] != null) {
                                reallongitude = nextNextLine[50];
                            } else {
                                reallongitude = " ";
                            }
                    } catch(IOException e) {
                        e.printStackTrace();
                        System.out.println("errrroeoeeer");
                    }

                    createRat(uniquekey, realcreatedDate, realLocationType, realincidentZip,
                                    realincidentAddress, realcity, realborough, reallatitude, reallongitude);
                        }
                        Intent intent = new Intent(RegisterRat.this, Welcome.class);
                        startActivity(intent);
                    }

        });

        ratCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterRat.this, MainActivity.class));
            }
        });


    }

    /**
     * Creating new user node under 'users'
     */
    private void createRat(String uniquekey, String realcreatedDate, String realLocationType , String realincidentZip, String realincidentAddress, String realcity,
                           String realborough, String reallatitude, String reallongitude) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            ratId = uniquekey;

        Rat rat = new Rat(ratId, realcreatedDate, realLocationType,realincidentZip, realincidentAddress ,realcity, realborough, reallatitude, reallongitude);

        addRatChangeListener();

        mFirebaseDatabase.child(ratId).setValue(rat);

    }

    /**
     * User data change listener
     */
    private void addRatChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(ratId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Rat rat = dataSnapshot.getValue(Rat.class);

                // Check for null
                if (rat == null) {
                    Log.e("TAG", "User data is null!");
                    return;
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("TAG", "Failed to read user", error.toException());
            }
        });
    }

}
