package com.example.happyfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class DashboardAct extends AppCompatActivity {

    BottomNavigationView nav_bar;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    WorkoutFrag wf;
    ChalFragNav cf;
    AcctNavFrag af;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#38312C")));
        getSupportActionBar().setTitle("DASHBOARD");

        wf = new WorkoutFrag();
        cf = new ChalFragNav();
        af = new AcctNavFrag();

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.navfrag, wf);

        fragmentTransaction.addToBackStack(wf.toString());
        fragmentTransaction.commit();

        nav_bar = (BottomNavigationView) findViewById(R.id.navi);

        nav_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_ex:
                    {


                        fragmentManager = getSupportFragmentManager();

                        fragmentTransaction = fragmentManager.beginTransaction();

                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                        fragmentTransaction.replace(R.id.navfrag, wf);

                        fragmentTransaction.addToBackStack(wf.toString());
                        fragmentTransaction.commit();

                        return true;
                    }
                    case R.id.nav_chal:
                    {

                        fragmentManager = getSupportFragmentManager();

                        fragmentTransaction = fragmentManager.beginTransaction();

                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);

                        fragmentTransaction.replace(R.id.navfrag, cf);

                        fragmentTransaction.addToBackStack(cf.toString());
                        fragmentTransaction.commit();

                        return true;
                    }
                    case R.id.nav_act:
                    {
                        fragmentManager = getSupportFragmentManager();

                        fragmentTransaction = fragmentManager.beginTransaction();

                        fragmentTransaction.remove(wf);

                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);

                        fragmentTransaction.replace(R.id.navfrag, af);

                        fragmentTransaction.addToBackStack(af.toString());
                        fragmentTransaction.commit();

                        return true;
                    }
                    default:
                    {
                        return false;
                    }
                }
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.optionsmenu_home, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem Item)
    {
        switch (Item.getItemId()) {
            case R.id.menulogout:
            {
                Uri number = Uri.parse("tel: 03341234567");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
                return true;
            }
            default: {
                return super.onOptionsItemSelected(Item);
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
