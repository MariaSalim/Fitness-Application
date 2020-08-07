package com.example.happyfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class WorkoutFrag extends Fragment
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.workout_frag, null);

        ImageView iv = (ImageView) vg.findViewById(R.id.Work);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                toWorkout();
            }
        });

        ImageView iv1 = (ImageView) vg.findViewById(R.id.Diet);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                toDietPlans();
            }
        });

        ImageView iv2 = (ImageView) vg.findViewById(R.id.Tip);

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                toTips();
            }
        });

        return vg;
    }

    private void toTips() {
        Intent i = new Intent(getContext(), TipsAct.class);
        startActivity(i);
    }

    public void toWorkout()
    {
        Intent i = new Intent(getContext(), Workout.class);
        startActivity(i);
    }
    public void toDietPlans()
    {
        Intent i = new Intent(getContext(), DietPlansAct.class);
        startActivity(i);
    }

}
