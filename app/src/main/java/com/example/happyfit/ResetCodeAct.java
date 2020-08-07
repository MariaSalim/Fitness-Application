
package com.example.happyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ResetCodeAct extends AppCompatActivity {
    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    EditText e, e1, e2;

    String un, sc, npc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_code);

        getSupportActionBar().hide();

        dbHelper = new DatabaseHelper(this);

        e = (EditText) findViewById(R.id.ed1login); //Username
        e1 = (EditText) findViewById(R.id.ed2login); //SecurityCode
        e2 = (EditText) findViewById(R.id.ed3login);//New Passcode
    }

    public void reset(View v) {
        String unamePattern = "\\w+";
        un = e.getText().toString();

        sc = e1.getText().toString();

        npc = e2.getText().toString();

        if (un.isEmpty() || sc.isEmpty() || npc.isEmpty()) {
            if (un.isEmpty()) {
                e.setError("You cannot leave this field Empty");
            }
            if (sc.isEmpty()) {
                e1.setError("You cannot leave this field Empty");
            }
            if (npc.isEmpty()) {
                e2.setError("You cannot leave this field Empty");
            }

            Functions.AD(this, "EMPTY FIELDS", "You cannot leave this field empty");

        }
        else if(!(un.matches(unamePattern)) || npc.length()<4)
        {
            if(!(un.matches(unamePattern))) {
                e.setError("You cannot leave this field Empty");

            }
            else
            {
                e2.setError("Passcode should be of atleast 4 digits");
            }
            Functions.AD(this, "", "Please Fill All Fields Correctly");
        }
        else if (!(LoginUserChk()))
        {
            Functions.AD(this, "Incorrect UserName or Security Code", "");
        }
        else if(!(SecCodeChk())) {
            Functions.AD(this, "WRONG SECURITY CODE", "");
        }
        else
        {
            Toast.makeText(this, "Passcode Changed", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, HomeAct.class);
            Functions.setUserName(this, un, npc);
            startActivity(i);
        }

    }
    public boolean LoginUserChk()
    {
        db = dbHelper.getReadableDatabase();
        List<String> usersList = new ArrayList();

        String sql = "SELECT "+DatabaseContract.Users.COL_USERNAME+","+DatabaseContract.Users.COL_PASSCODE +" FROM " +  DatabaseContract.Users.TABLE_NAME + " WHERE " + DatabaseContract.Users.COL_USERNAME+ " =? "+ " AND " + DatabaseContract.Users.COL_PASSCODE +" =?";
        Cursor c = db.rawQuery(sql, new String[] {un,sc});

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

    public boolean SecCodeChk()
    {
        db = dbHelper.getReadableDatabase();
        List<String> usersList = new ArrayList();

        ContentValues args = new ContentValues();

        args.put(DatabaseContract.Users.COL_PASSCODE,npc);

        String[] wherearg={un};

        Integer count= db.update(DatabaseContract.Users.TABLE_NAME, args, DatabaseContract.Users.COL_USERNAME + "=?",wherearg);

        if (count > 0)
        {
            Toast.makeText(this, "Updated" , Toast.LENGTH_SHORT).show();
        }
        if (count>0)
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
