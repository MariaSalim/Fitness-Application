package com.example.happyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class HomeAct extends AppCompatActivity
{

    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    Intent i1;

    String bmi;
    Double bmid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbHelper = new DatabaseHelper(this);

        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#38312C")));

        getSupportActionBar().hide();

        Intent i = getIntent();
        i1= new Intent(this, DashboardAct.class);

        bmi = i.getStringExtra("bmi");

        if(bmi==null)
        {
            bmi = getBMI();
        }

        bmid =Double.parseDouble(bmi);


        TextView t = (TextView) findViewById(R.id.tvun);

        t.setText("CALCULATED BMI");

        TextView t1 = (TextView) findViewById(R.id.tvbmi1);

        t1.setText(Functions.round(bmid));

        if(bmid <19)
        {
            t1.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        }
        else if (bmid>19 && bmid <25)
        {
            t1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        }
        else if (bmid>25 && bmid <30)
        {
            t1.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
        }
        else
        {
            t1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();
    }


    public String getBMI()
    {
        String B="";

        db = dbHelper.getReadableDatabase();

        String[] columns = {DatabaseContract.UserDetails.COL_BMI};
        Cursor c = db.query(DatabaseContract.UserDetails.TABLE_NAME, columns, null, null, null, null, null);
        while (c.moveToNext())
        {
            B = c.getString(0);
        }
        return B;
    }

    public  void toDashboard(View v)
    {
        Handler handler;
        final ProgressDialog progress = ProgressDialog.show(this,
                "", "Loading", false, false);

        handler=new Handler();
        getSupportActionBar().hide();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                    progress.cancel();
                    startActivity(i1);

                finish();
            }
        },3000);

    }

}
