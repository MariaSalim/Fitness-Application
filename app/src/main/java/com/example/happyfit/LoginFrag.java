package com.example.happyfit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class LoginFrag extends Fragment {

    EditText e, e1;

    String s,p;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    Boolean switchState;

    SharedPreferences prf;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.login_frag,null);

        prf = this.getActivity().getSharedPreferences("User",MODE_PRIVATE);

        dbHelper = new DatabaseHelper(getActivity());

        e = (EditText) vg.findViewById(R.id.ed1login);
        e1 = (EditText) vg.findViewById(R.id.ed2login);

        TextView t = (TextView) vg.findViewById(R.id.fptv);

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getContext(), ResetCodeAct.class);
                startActivity(i);
            }
        });


        ImageButton b = (ImageButton) vg.findViewById(R.id.btnl);

        final Switch sw= (Switch) vg.findViewById(R.id.sw);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unamePattern = "\\w+";
                s = e.getText().toString();

                p = e1.getText().toString();

                if(p.isEmpty() || s.isEmpty())
                {
                    if((s.isEmpty()) || (p.isEmpty()))
                    {
                        if(s.isEmpty())
                        {
                            e.setError("You cannot leave this field Empty");
                        }
                        if(p.isEmpty())
                        {
                            e1.setError("You cannot leave this field Empty");
                        }

                        Functions.AD(getContext(), "EMPTY FIELDS", "You cannot leave this field empty");

                    }

                }
                else if(!(s.matches(unamePattern)))
                {
                    e.setError("You cannot leave this field Empty");
                    Functions.AD(getContext(), "", "Please Fill All Fields Correctly");
                }
                else if(!(LoginUserChk()))
                {
                    Functions.AD(getContext(), "Incorrect Username or Password", "");
                }
                else
                {

                    switchState = sw.isChecked();

                    if (switchState)
                    {
                        Functions.setUserName(getContext(), s, p);
                    }

                    Intent i= new Intent(getActivity(), HomeAct.class);
                    getActivity().startActivity(i);
                }

            }
        });

        return vg;
    }

    public boolean LoginUserChk()
    {
        db = dbHelper.getWritableDatabase();
        List<String> usersList = new ArrayList();

        String sql = "SELECT "+DatabaseContract.Users.COL_USERNAME+","+DatabaseContract.Users.COL_PASSCODE +" FROM " +  DatabaseContract.Users.TABLE_NAME + " WHERE " + DatabaseContract.Users.COL_USERNAME+ " =? "+ " AND " + DatabaseContract.Users.COL_PASSCODE +" =?";
        Cursor c = db.rawQuery(sql, new String[] {s, p});

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

