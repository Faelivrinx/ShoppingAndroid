package com.example.dominik.prontoshop.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "shop_android.db";
    private static DatabaseHelper mDatabaseInstance = null;

    public static DatabaseHelper newInstance(Context context){
        if(mDatabaseInstance == null){
            mDatabaseInstance = new DatabaseHelper(context.getApplicationContext());
        }

        return mDatabaseInstance;
    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
