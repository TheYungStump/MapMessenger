package com.example.vlisn.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.RadioButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by vlisn on 9/24/2017.
 */

public class Register extends AppCompatActivity {
    Button bCreate, bCancel;
    EditText etEmail, etPassword, etName;
    FirebaseAuth mAuth;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        etEmail = (EditText) findViewById(R.id.newEmail);
        etPassword = (EditText) findViewById(R.id.newPassword);
        etName = (EditText) findViewById(R.id.name);
        bCreate = (Button) findViewById(R.id.buttonCreate);
        bCancel = (Button) findViewById(R.id.buttonCancel);
        final RadioButton adminButton = (RadioButton) findViewById(R.id.adminButton);
        RadioButton userButton = (RadioButton) findViewById(R.id.userButton);
        mAuth = FirebaseAuth.getInstance();


        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("M5");

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
        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                boolean access = false;
                    if (adminButton.isChecked()) {
                        access = true;
                    } else {
                        access = false;
                    }
                // Check for already existed userId
                if (TextUtils.isEmpty(userId)) {
                    createUser(name, email, password, access);
                }
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }

        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });


    }

    /**
     * Creating new user node under 'users'
     */
    private void createUser(String name, String email, String password, boolean access) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        User user = new User(name, email, password, access);

        addUserChangeListener();

        mFirebaseDatabase.child(userId).setValue(user);

    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
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


//    public void onClickCancel(View view) {
//        startActivity(new Intent(Register.this, MainActivity.class));
//    }
//
//    public void onClickCreate(View view) {
//        String email = etEmail.getText().toString().trim();
//        String password = etPassword.getText().toString().trim();
//        if (etEmail.getText().toString().equals("")
//                || etPassword.getText().toString().equals("")||
//                etName.getText().toString().equals("") ) {
//            //error
//            Context context = getApplicationContext();
//            CharSequence text = "No empty usernames or passwords, please!";
//            int duration = Toast.LENGTH_SHORT;
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//        }
//
//    }


