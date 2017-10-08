package com.example.vlisn.m4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vlisn.m4.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mason Buchanan on 10/2/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version, Name, and Table Name
    private static final int VERSION = 1;
    private static final String NAME = "UserManager.db";
    private static final String USER = "User";

    // Table Columns
    private static final String USER_ID = "User_ID";
    private static final String USER_NAME = "User_Name";
    private static final String USER_PASSWORD = "User_Password";
    private static final String ACTUAL_NAME = "Name";

    private String CREATE_USER_TABLE = "CREATE TABLE" + USER + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_NAME + " TEXT," + USER_PASSWORD + " TEXT,"
            + ACTUAL_NAME + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS" + USER;

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exists
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, user.getId());
        values.put(USER_NAME, user.getUserName());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(ACTUAL_NAME, user.getActualName());

        // Adding Row
        db.insert(USER, null, values);
       //db.close();
    }

    public List<User> getUsers() {
        // Creates array of columns to extract
        String[] columns = {USER_ID, USER_NAME, USER_PASSWORD, ACTUAL_NAME};

        // Creates sorting order
        String sortOrder = USER_NAME + " ASC";

        // This list will contain all of the users
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Cursor by which we will fetch records from user table
        Cursor cursor = db.query(USER, columns, null, null, null, null, sortOrder);

        // Adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
                user.setActualName(cursor.getString(cursor.getColumnIndex(ACTUAL_NAME)));
            } while (cursor.moveToNext());
        }
        //cursor.close();
        //db.close();

        return userList;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getUserName());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(ACTUAL_NAME, user.getActualName());

        // Updating Row
        db.update(USER, values, USER_ID + " =?",
                new String[] {String.valueOf(user.getId())});
        //db.close();
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete user by id value
        db.delete(USER, USER_ID  + " =?",
                new String[] {String.valueOf(user.getId())});
        //db.close();
    }

    // Checks to see if User exists
    public boolean checkUser(String name, String password) {
        String[] columns = {USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();

        // Selection Criteria
        String selection = USER_NAME + " =?" + " AND " + USER_PASSWORD;

        // Selection Arg
        String[] selectionsArgs = {name, password};

        // A query that will return the number of occurrences of that username
        Cursor cursor = db.query(USER, columns, selection, selectionsArgs, null, null, null);
        int count = cursor.getCount();
        //cursor.close();
        //db.close();

        if (count > 0) {
            return true;
        }
        return false;
    }
}
