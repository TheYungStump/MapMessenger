package com.example.vlisn.m4;


import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ravi Tamada on 07/10/16.
 * www.androidhive.info
 */

@IgnoreExtraProperties
public class User {

    public String name;
    public String email;
    public String password;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    public String getEmail() { return email;}
    public String getPassword() { return password;}
}