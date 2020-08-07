package com.example.happyfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class PrivacyPolicy extends AppCompatActivity {

    String un,pc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        getSupportActionBar().hide();
        Intent i= getIntent();
        un= i.getStringExtra("un");
        pc=i.getStringExtra("pc");
    }
    @Override
    public void onBackPressed()
    {
        // super.onBackPressed();
    }
    public void next(View v)
    {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//Create channel in which to send push notifications
        String CHANNEL_ID = "HappyFit";

// only use NotificationChannel when Api Level >= 26
        if(Build.VERSION.SDK_INT >= 26) {
            CharSequence name = "Happy Fit";
            String Description = "Fitness App";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

//Send push notification
        Notification notify = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle("Account Created")
                .setContentText("Thank you for becoming a part of our Happy Fit Family!")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notify);
        Functions.setUserName(this, un, pc);
        Intent i = new Intent(this, MainScreen.class);
        startActivity(i);
    }
}
