package com.example.happyfit;

import android.provider.BaseColumns;

public class DatabaseContract {
    public DatabaseContract() {}

    /* Inner class that defines the table contents */
    public static abstract class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COL_USERNAME  = "UserName";
        public static final String COL_FULLNAME = "FullName";
        public static final String COL_SECURITYCODE = "SecurityCode";
        public static final String COL_PASSCODE="PassCode";

    }

    public static abstract class UserDetails implements BaseColumns {
        public static final String TABLE_NAME = "userdetails";
        public static final String COL_USERNAME  = "UserName";
        public static final String COL_HEIGHT = "Height";
        public static final String COL_WEIGHT = "Weight";
        public static final String COL_GENDER="Gender";
        public static final String COL_BIRTHDAY="Birthday";
        public static final String COL_BMI="BMI";


    }


}
