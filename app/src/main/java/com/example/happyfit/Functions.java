package com.example.happyfit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Functions
{

    public static void animate(final ImageView imageView, final int images[], final int imageIndex, final RelativeLayout r, final int Colors[], final int textToShow[], final TextView tv)
    {
        //imageView <-- The View which displays the images
        //images[] <-- Holds R references to the images to display
        //imageIndex <-- index of the first image to show in images[]

        int fadeInDuration = 300; // Configure time values here
        int timeBetween = 3000;
        int fadeOutDuration = 300;

        imageView.setImageResource(images[imageIndex]);
        r.setBackgroundResource(Colors[imageIndex]);
        tv.setText(textToShow[imageIndex]);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
        fadeIn.setDuration(fadeInDuration);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
        fadeOut.setStartOffset(fadeInDuration + timeBetween);
        fadeOut.setDuration(fadeOutDuration);

        AnimationSet animation = new AnimationSet(false); // change to false
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);

        r.setAnimation(animation);
        tv.setAnimation(animation);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation)
            {
                if (images.length - 1 > imageIndex)
                {
                    animate(imageView, images, imageIndex + 1,r, Colors, textToShow, tv); //Calls itself until it gets to the end of the array
                }
                else
                {
                    animate(imageView, images, 0,r, Colors, textToShow, tv);  //Calls itself to start the animation all over again
                }
            }
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
        });
    }
    public static void AD(Context c, String t, String Message)
    {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(c, R.style.alertDialog);

        builder.setTitle(t);
        builder.setMessage(Message);
        builder.setIcon(R.drawable.alert);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    public static final String PREF_USER_NAME= "User";
    public static final String PREF_PASSCODE= "Passcode";

    private static SharedPreferences getSharedPreferences(Context ctx)
    {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public static void setUserName(Context ctx, String userName, String Pc)
    {
         SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();

         editor.putString(PREF_USER_NAME, userName);
         editor.putString(PREF_PASSCODE, Pc);

         editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
    public static void clearCredentials(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }
    public static Double calBmi(Double h, Double w)
    {
        /*Formula: weight (kg) / [height (m)]2*/
        Double b;

        b = w/ Math.pow(h, 2.0);

        return b;
    }
    public static String round(Double d)
    {
        DecimalFormat df = new DecimalFormat("###.###");
        String s= df.format(d);

        return s;
    }
}
