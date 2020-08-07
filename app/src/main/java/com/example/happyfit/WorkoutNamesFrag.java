package com.example.happyfit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkoutNamesFrag extends Fragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    String[] ListviewTitle = new String[]{
            "Basic Step Aerobics", "Squats","Push ups"
    };
    // description
    String[] ListviewDescription = new String[]{
            " "," "," "," "
    };
    // images
    int[] ListviewImages = new int[]{
            R.drawable.wrk_aerobics, R.drawable.wrk_squats, R.drawable.wrk_pushup
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.workout_names_frag, null);


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
        ListView simpleListview = (ListView) vg.findViewById(R.id.lv_wrkouts);
        simpleListview.setAdapter(simpleAdapter);
        simpleListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(position==0)
                {
                    String url="https://www.youtube.com/watch?v=965qZScyVVI";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                else if(position==1)
                {
                    String url="https://www.youtube.com/watch?v=WVTO2YGYoaE";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                else
                {
                    String url="https://www.youtube.com/watch?v=AhdtowFDKT0";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }

            }
        });


        Button b = (Button) vg.findViewById(R.id.btngyms);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),GymsInIslamabad.class);
                startActivity(i);
            }
        });
        return vg;

    }
}
