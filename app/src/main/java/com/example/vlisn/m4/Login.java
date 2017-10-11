package com.example.vlisn.m4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static android.R.attr.value;

/**
 * Created by vlisn on 9/24/2017.
 */

public class Login extends AppCompatActivity {

    Button loginB, cancelB;
    EditText etEmail, etPassword;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    //boolean exists = true;
    User details;
    String email;
    String password;
    final boolean[] exists = new boolean[1];
    public void onCreateLogin(){
        if(!(details.getEmail().equals(email)) || !(details.getPassword().equals(password))) {
            Context context = getApplicationContext();
            CharSequence text = "email or password does not exist!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loginB = (Button) findViewById(R.id.loginButton);
        cancelB = (Button) findViewById(R.id.cancelButton);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference();


        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                mFirebaseDatabase.child("users").child(email.substring(0, email.indexOf('@')))
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //System.out.println(email);
                                //System.out.println(password);
                                if (dataSnapshot.exists()) {
                                    //System.out.println("reached with email " + dataSnapshot.child("email"));
                                    String emaildata = dataSnapshot.child("email").toString();
                                    String passworddata = dataSnapshot.child("password").toString();
                                    if (emaildata.equals(email) && passworddata.equals(password)) {
                                        //System.out.println("reached with email " + dataSnapshot.child("email"));
                                        //System.out.println(dataSnapshot.child("password"));
                                        startActivity(new Intent(Login.this, Welcome.class));
                                    } else {
                                        Context context = getApplicationContext();
                                        CharSequence text = "email or password does not exist!";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                    }
                                }



                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

//                mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        mFirebaseDatabase.child("users")
//                                .addChildEventListener(new ChildEventListener() {
//                                    @Override
//                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                                        if (dataSnapshot.hasChildren()) {
//                                            details = dataSnapshot.getValue(User.class);
//                                            if(details.getEmail().equals(email) && details.getPassword().equals(password)) {
//                                                exists[0] = false;
//                                                startActivity(new Intent(Login.this, Welcome.class));
//                                            } else{
//                                                exists[0] = true;
//                                            }
//                                        }
//
//                                    }
//
//
//                                    @Override
//                                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                                    }
//
//                                    @Override
//                                    public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                                    }
//
//                                    @Override
//                                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                                    }
//
//                                    @Override
//                                    public void onCancelled(DatabaseError databaseError) {
//
//                                    }
//                                });
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
                //System.out.println(exists[0]);
//                if (exists[0]) {
//                    System.out.println("IsTrue?" + exists[0]);
//                    Context context = getApplicationContext();
//                    CharSequence text = "email or password does not exist!";
//                    int duration = Toast.LENGTH_SHORT;
//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.show();
//                    //exists[0] = false;
//                }

            }
        });
    }
}
