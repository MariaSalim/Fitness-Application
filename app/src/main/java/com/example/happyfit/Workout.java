package com.example.happyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Workout extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        getSupportActionBar().hide();
    }
}
