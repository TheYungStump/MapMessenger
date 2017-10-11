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
    public boolean access;
    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email, String password, Boolean access) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.access = access;
    }
    public String getEmail() { return email;}
    public String getPassword() { return password;}
}