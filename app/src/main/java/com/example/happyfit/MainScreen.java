package com.example.happyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainScreen extends AppCompatActivity {

    ArrayList<String> a; // Height

    ArrayList<String> a1; //Weight

    boolean flag= false;

    EditText e;
    EditText e1, e2; //height

    EditText e3; //weight

    String s;

    String s1,s2; //height

    String s3; //weight

    Double bmi, hg, wg;

    SQLiteDatabase db;
    DatabaseHelper dbHelper;


    String height, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().hide();


        dbHelper = new DatabaseHelper(this);


        //Height
        e= (EditText) findViewById(R.id.datep);

        e1 = (EditText) findViewById(R.id.edh);

        e2 = (EditText) findViewById(R.id.edh1);

        e3 = (EditText) findViewById(R.id.edw);

        s=e.getText().toString();

        Spinner sp = (Spinner) findViewById(R.id.sph);
        String[] spv = getResources().getStringArray(R.array.sphei);

        a = new ArrayList<String>();

        a.add(spv[0]);
        a.add(spv[1]);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, a);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String h = a.get(position);
                if(h.equalsIgnoreCase("in"))
                {
                    height="in";
                    e1.setHint(getResources().getString(R.string.ft));
                    e2.setHint(getResources().getString(R.string.in));
                    e2.setVisibility(View.VISIBLE);
                    flag=true;
                }
                else
                {
                    flag=false;
                    height="cm";
                    e1.setHint("");
                    e2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                height="cm";
                e2.setVisibility(View.GONE);
            }
        });

        //height

        //weight

        e3 = (EditText) findViewById(R.id.edw);

        Spinner sp1 = (Spinner) findViewById(R.id.spw);

        String[] spv1 = getResources().getStringArray(R.array.spwei);

        a1 = new ArrayList<String>();

        a1.add(spv1[0]);
        a1.add(spv1[1]);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, a1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp1.setAdapter(adapter1);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String w = a1.get(position);
                if(w.equalsIgnoreCase("kg"))
                {
                    weight="kg";
                }
                else
                {
                    weight="lbs";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                height="lbs";
            }
        });
        //weight

        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String date = day + " / " + month + " / " + year;
                e.setText(date);
            }
        };

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainScreen.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, date1, year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

    }
    public void onBackPressed()
    {
        // super.onBackPressed();
    }
    public void toHome(View v)
    {
        boolean chk=true;

        s=e.getText().toString();
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        s3=e3.getText().toString();

        if(s.isEmpty() || s1.isEmpty() || s3.isEmpty() )
        {
            if(s.isEmpty())
            {
                e.setError("You cannot leave this field Empty");
            }
            if(s1.isEmpty())
            {
                e1.setError("You cannot leave this field Empty");
            }
            if(s3.isEmpty())
            {
                e3.setError("You cannot leave this field Empty");
            }
            chk=false;
            Functions.AD(this, "EMPTY FIELDS", "You cannot leave this field empty");
        }
        else if(Integer.parseInt(s1)<0 || Integer.parseInt(s3)<0 )
        {
            if(Integer.parseInt(s1)<0)
            {
                e1.setError("You cannot enter a negative value");
            }
            if(Integer.parseInt(s3)<0)
            {
                e3.setError("You cannot enter a negative value");
            }
            chk=false;
            Functions.AD(this, "", "PLease Fill All Fields Correctly");
        }
        if(flag)
        {
            if(s2.isEmpty())
            {
                chk=false;
                e2.setError("You cannot leave this field Empty");
                Functions.AD(this, "EMPTY FIELDS", "You cannot leave this field empty");
            }
            else if(Integer.parseInt(s2)<0)
            {
                chk=false;
                e2.setError("You cannot enter a negative value");
                Functions.AD(this, "", "PLease Fill All Fields Correctly");
            }
        }
        if(chk)
        {
            if(height.equalsIgnoreCase("cm"))
            {
                hg= Double.parseDouble(s1);
                hg= hg/100;
            }
            else
            {
                /*(((feet * 12) + inches) * .0254)*/
                Double ft, in;
                ft = Double.parseDouble(s1);
                in = Double.parseDouble(s2);
                hg= ((ft * 12) + in) * 0.0254;
            }
            if(weight.equalsIgnoreCase("lbs"))
            {
                Double lb = Double.parseDouble(s3);
                wg = lb * 0.453592;
            }
            else
            {
                Double kg = Double.parseDouble(s3);
                wg= kg;
            }

            bmi = Functions.calBmi(hg, wg);

            insertDet();
        }
    }
    public void insertDet()
    {
        db = dbHelper.getWritableDatabase();

        RadioGroup rgrp = (RadioGroup) findViewById(R.id.rg);

        int selected=rgrp.getCheckedRadioButtonId();

        RadioButton rb=(RadioButton) findViewById(selected);

        ContentValues values = new ContentValues();

        values.put(DatabaseContract.UserDetails.COL_USERNAME,Functions.getUserName(this));
        values.put(DatabaseContract.UserDetails.COL_HEIGHT,hg.toString());
        values.put(DatabaseContract.UserDetails.COL_WEIGHT,wg.toString());
        values.put(DatabaseContract.UserDetails.COL_BMI,bmi.toString());
        values.put(DatabaseContract.UserDetails.COL_GENDER, rb.getText().toString());
        values.put(DatabaseContract.UserDetails.COL_BIRTHDAY,s);

        long newRowId = db.insert(DatabaseContract.UserDetails.TABLE_NAME, null, values);

        if (newRowId > 0)
        {
            Intent i= new Intent(this, HomeAct.class);
            i.putExtra("bmi", String.valueOf(bmi));
            startActivity(i);
        }
        else
        {
            Functions.AD(this, " 404", "An Error Occured While processing you Details");
        }

    }
}
