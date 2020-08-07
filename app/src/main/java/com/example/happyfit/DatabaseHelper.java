package com.example.happyfit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Project.db";

    private static final String CREATE_TBL_USERS = "CREATE TABLE "
            + DatabaseContract.Users.TABLE_NAME + " ("
            + DatabaseContract.Users.COL_USERNAME + " TEXT PRIMARY KEY, "
            + DatabaseContract.Users.COL_FULLNAME + " TEXT NOT NULL, "
            + DatabaseContract.Users.COL_PASSCODE + " TEXT NOT NULL, "
            + DatabaseContract.Users.COL_SECURITYCODE+ " TEXT NOT NULL )";

    private static final String CREATE_TBL_USERDETAILS = "CREATE TABLE "
            + DatabaseContract.UserDetails.TABLE_NAME + " ("
            + DatabaseContract.UserDetails.COL_USERNAME + " TEXT PRIMARY KEY, "
            + DatabaseContract.UserDetails.COL_HEIGHT + " TEXT NOT NULL, "
            + DatabaseContract.UserDetails.COL_WEIGHT + " TEXT NOT NULL, "
            + DatabaseContract.UserDetails.COL_BMI + " TEXT NOT NULL, "
            + DatabaseContract.UserDetails.COL_GENDER+ " TEXT NOT NULL, "
            + DatabaseContract.UserDetails.COL_BIRTHDAY + " TEXT NOT NULL )";


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBL_USERS);
        db.execSQL(CREATE_TBL_USERDETAILS);

        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
    }

}

