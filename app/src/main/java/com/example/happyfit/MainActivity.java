package com.example.happyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    TextView tv;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        RelativeLayout rl= (RelativeLayout) findViewById(R.id.rl);

        if(Functions.getUserName(MainActivity.this).length()>0)
        {
            Intent i = new Intent(this, DashboardAct.class);
            startActivity(i);

        }
        else {
            // final int ColorsToShow[]={R.color.s1,R.color.s2,R.color.s3};
            final int BgToShow[] = {R.drawable.moonbg, R.drawable.cbg, R.drawable.wbg};

            iv = (ImageView) findViewById(R.id.IV);
            tv = (TextView) findViewById(R.id.tv2);


            final int imagesToShow[] = {R.drawable.moon1, R.drawable.cluttery, R.drawable.w};
            final int textToShow[] = {R.string.sb, R.string.eb, R.string.sl};
            Functions.animate(iv, imagesToShow, 0, rl, BgToShow, textToShow, tv);

        }
    }
    public void OnJoin(View v)
    {
        Intent i = new Intent(this, JoinAct.class);
        startActivity(i);
    }
}
