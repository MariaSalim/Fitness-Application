package com.example.happyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DietPlansAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plans);
        getSupportActionBar().hide();

        String[] lvitems = new String[]{
            "A 7-Day, 1,200 Calorie Meal Plan", "A 1,500-Calorie Diet","A 2,000 Calorie Diet","7-Day Diet Meal Plan: 2,200 Calories"
        };

        ListView l = (ListView) findViewById(R.id.lvDietp);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lvitems){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);
                tv.setTypeface(Typeface.MONOSPACE);
                tv.setTextSize(18);

                // Generate ListView Item using TextView
                return view;
            }
        };

        l.setAdapter(adp);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(position==0)
                {
                    String url="https://www.goodhousekeeping.com/health/diet-nutrition/g4351/1200-calorie-diet-plan/?slide=4";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                else if(position==1)
                {
                    String url="https://www.healthline.com/nutrition/1500-calorie-diet#foods-to-eat";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                else if(position==2)
                {
                    String url="https://www.healthline.com/nutrition/2000-calorie-diet";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                else
                {
                    String url="http://www.eatingwell.com/article/287772/7-day-diet-meal-plan-to-lose-weight-2200-calories/";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            }
        });

    }
}
