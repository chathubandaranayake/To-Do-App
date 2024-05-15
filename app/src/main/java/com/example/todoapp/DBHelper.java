package com.example.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "todo.db";
    public DBHelper(Context context) {
        super(context, "todo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    public Boolean instertData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null,values);
        if (result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});


        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs = {username, password};
        Cursor cursor = db.rawQuery("select * from users where username = ? and password = ?", selectionArgs);

        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

}
