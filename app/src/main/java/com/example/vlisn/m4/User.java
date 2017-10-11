package com.example.vlisn.m4;

/**
 * Created by Mason Buchanan on 10/2/2017.
 */

public class User {

    private String userName;
    private String password;
    private String actualName;

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
