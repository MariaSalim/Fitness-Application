package com.example.happyfit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChalFragNav extends Fragment
{
    String[] ListviewTitle = new String[]{
            "GET FIT CHALLENGE", "INTENCE CORE CHALLENGE","SUMMER SHRED CHALLENGE", "HOURGLASS CHALLENGE", "SLIM THIGH PROGRAM"
    };
    // description
    String[] ListviewDescription = new String[]{
            "","","","",""
    };
    // images
    int[] ListviewImages = new int[]{
            R.drawable.chal1, R.drawable.chal2, R.drawable.chal3, R.drawable.chal4, R.drawable.chal5
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.chal_frag_nav, null);

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String, String>>();
        for (int x = 0; x < ListviewImages.length; x++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("ListTitle",ListviewTitle[x]);
            hm.put("ListDescription",ListviewDescription[x]);
            hm.put("ListImages",Integer.toString(ListviewImages[x]));
            aList.add(hm);
        }

        String[] from = {
                "ListImages","ListTitle","ListDescription"
        };
        int[] to = {
                R.id.listview_images,R.id.Title,R.id.DEscription
        };

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),aList, R.layout.listview_items,from,to);
        ListView simpleListview = (ListView) vg.findViewById(R.id.lv_chal);
        simpleListview.setAdapter(simpleAdapter);
        simpleListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(position==0)
                {
                    chalGetFit();
                }
                else if(position==2)
                {
                    chalIntence();
                }
                else if(position==3)
                {
                    chalSummer();
                }
                else if(position==4)
                {
                    chalHourglass();
                }
                else
                {
                    chalslimThigh();
                }

            }
        });

        return vg;
    }
    public void chalGetFit()
    {
        String url="https://www.youtube.com/watch?v=ixKRye1PqXU&list=PLAFs3kxY4h1_cd7VDwNX2X4C_16MfAHS_";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void chalIntence()
    {
        String url="https://www.youtube.com/watch?v=ge1ALhE-Fqs&list=PLAFs3kxY4h18C-BEvQWBxwNZYPLK_BDQF";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void chalSummer()
    {
        String url="https://www.youtube.com/watch?v=cIuiQyfKBTg&list=PLAFs3kxY4h19jbKjLS9vmDF2W53RZUfAg";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void chalHourglass()
    {
        String url="https://www.youtube.com/watch?v=cO2RqgslEjk&list=PLAFs3kxY4h19gtEM_x1IE2OrYS-Iae49E";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void chalslimThigh()
    {
        String url="https://www.youtube.com/watch?v=EUruBzhv7Kk&list=PLAFs3kxY4h1-kLMG2F9GJBg4cqr-i7T8w";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
