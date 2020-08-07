package com.example.happyfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class JoinAct extends AppCompatActivity {
    int ftd=1;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    RelativeLayout r;

    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        getSupportActionBar().hide();

        r = (RelativeLayout) findViewById(R.id.btnrl);

        b=(Button) findViewById(R.id.btntrans);

    }

    public void switchFrag(View v)
    {
        if(ftd==0)
        {
            LoginFrag lf = new LoginFrag();

            fragmentManager = getSupportFragmentManager();

            fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.fragrl, lf);

            fragmentTransaction.addToBackStack(lf.toString());
            ftd=1;

            b.setText("SIGN UP");

        }
        else
        {
            SignupFrag sf = new SignupFrag();

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);

            fragmentTransaction.replace(R.id.fragrl, sf);

            fragmentTransaction.addToBackStack(sf.toString());

            ftd=0;

            b.setText("LOGIN");

        }

        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
