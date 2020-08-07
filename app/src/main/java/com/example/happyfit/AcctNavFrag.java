package com.example.happyfit;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class AcctNavFrag extends Fragment
{
    SQLiteDatabase db;
    DatabaseHelper dbHelper;


    String[] lvItems = new String[]
            {
              "Change Passcode", "Logout",
            };

    ArrayAdapter<String> adp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.acct_nav_frag, null);


        dbHelper = new DatabaseHelper(getActivity());

        TextView t = (TextView) vg.findViewById(R.id.tvname);

        t.setText("Hi, "+getName());
        ListView l = (ListView) vg.findViewById(R.id.lv_acct);

        adp = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,lvItems){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);
                tv.setTypeface(Typeface.MONOSPACE);
                tv.setTextSize(24);

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
                    //Change Passcode
                    Intent i = new Intent(getActivity(), ChangePasscode.class);
                    startActivityForResult(i, 1);

                }
                else
                {
                    Logout();
                }
            }
        });


        return vg;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK)
            {
                Functions.AD(getContext(), "PASSCODE UPDATED","");

            }
        }
    }
    public void Logout()
    {
        Functions.clearCredentials(getActivity());
        Toast.makeText(getContext(), "Logging Out", Toast.LENGTH_SHORT).show();
        Intent il = new Intent(getActivity(), JoinAct.class);
        startActivity(il);
    }
    public String getName()
    {
        String B="";

        db = dbHelper.getReadableDatabase();

        String[] columns = {DatabaseContract.Users.COL_FULLNAME};
        Cursor c = db.query(DatabaseContract.Users.TABLE_NAME, columns, null, null, null, null, null);
        while (c.moveToNext())
        {
            B = c.getString(0);
        }
        return B;
    }
}
