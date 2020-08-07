package com.example.happyfit;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SignupFrag extends Fragment
{
    Button b;
    EditText e1, e2, e3, e4;
    String fn, un, pc,sc;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.signup_frag,null);

        dbHelper = new DatabaseHelper(getActivity());

        b= (Button) vg.findViewById(R.id.btns);

        e1 = (EditText) vg.findViewById(R.id.ed3login); //Full Name
        e2 = (EditText) vg.findViewById(R.id.ed1login); // User Name
        e3 = (EditText) vg.findViewById(R.id.ed2login); //Passcode
        e4 = (EditText) vg.findViewById(R.id.ed4login); //Phone

        Log.d("Values", "----"+fn+un+pc+sc);

        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fn = e1.getText().toString();
                un = e2.getText().toString();
                pc = e3.getText().toString();
                sc = e4.getText().toString();

                String unamePattern = "\\w+";

                if((fn.isEmpty()) || (un.isEmpty()) || (pc.isEmpty()) || (sc.isEmpty()))
                {
                    if(fn.isEmpty())
                    {
                        e1.setError("You cannot leave this field Empty");
                    }
                    if(un.isEmpty())
                    {
                        e2.setError("You cannot leave this field Empty");
                    }
                    if(pc.isEmpty())
                    {
                        e3.setError("You cannot leave this field Empty");
                    }
                    if(sc.isEmpty())
                    {
                        e4.setError("You cannot leave this field Empty");
                    }

                    Functions.AD(getContext(), "EMPTY FIELDS", "You cannot leave this field empty");

                }
                else if(!(un.matches(unamePattern)) || pc.length()<4 || ChkUserName())
                {
                    if(!(un.matches(unamePattern)))
                    {
                        e2.setError("You can Only use Letters, Digits, and underscore");
                    }
                    if(ChkUserName())
                    {
                        e2.setError("UserName Already Exists!");
                        Functions.AD(getContext(), "UserName Already Exists!", "");
                        return;
                    }
                    if(pc.length()<4)
                    {
                        e3.setError("Please enter atleast 4 digits for Passcode");
                    }


                    Functions.AD(getContext(), "", "Please Fill All Fields Correctly");

                }
                else
                {
                    InsertIntoUsers();
                }



            }
        });

        return vg;
    }
    public void InsertIntoUsers()
    {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Users.COL_USERNAME,un);
        values.put(DatabaseContract.Users.COL_FULLNAME, fn);
        values.put(DatabaseContract.Users.COL_PASSCODE,pc);
        values.put(DatabaseContract.Users.COL_SECURITYCODE,sc);
        long newRowId = db.insert(DatabaseContract.Users.TABLE_NAME, null, values);
        if (newRowId > 0)
        {

            Intent i= new Intent(getActivity(), PrivacyPolicy.class);
            i.putExtra("un", un);
            i.putExtra("pc", pc);
            getActivity().startActivity(i);
        }
        else
        {
            Functions.AD(getContext(), " 404", "An Error Occured While Creating Your Account");
        }

        db.close();
    }

    public boolean ChkUserName()
    {
        db = dbHelper.getReadableDatabase();
        List<String> usersList = new ArrayList();
        String[] columns = { DatabaseContract.Users.COL_USERNAME};

        Cursor c = db.query(DatabaseContract.Users.TABLE_NAME,columns,DatabaseContract.Users.COL_USERNAME + "=?", new String[] {un}
                , null, null, null, null);
        if (c.getCount()==1)
        {
            db.close();
            return true;
        }
        else
        {
            db.close();
            return false;
        }
    }



}



