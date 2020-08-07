package com.example.happyfit;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GymsInIslamabad extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyms_in_islamabad);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng FitnessZoneGym= new LatLng(33.690684, 73.032830);
        mMap.addMarker(new MarkerOptions().position(FitnessZoneGym).title("Fitness Zone Gyme"));

        LatLng FitnessLoungeBluearea= new LatLng(33.707679, 73.056004);
        mMap.addMarker(new MarkerOptions().position(FitnessLoungeBluearea).title("Fitness Lounge Bluearea"));

        LatLng ExecutiveClub = new LatLng(33.691526, 73.010839);
        mMap.addMarker(new MarkerOptions().position(ExecutiveClub).title("Executive Club"));

        LatLng LifestyleGym = new LatLng(33.669345, 73.073938);
        mMap.addMarker(new MarkerOptions().position(LifestyleGym).title("LifeStyle Gym"));

        LatLng GymTown = new LatLng(33.697111, 73.012746);
        mMap.addMarker(new MarkerOptions().position(GymTown).title("Gym Town"));

        LatLng IsbGym = new LatLng(33.648830, 73.039999);
        mMap.addMarker(new MarkerOptions().position(IsbGym).title("Islamabad Gym"));

        LatLng ZK = new LatLng(33.731860, 73.079146);
        mMap.addMarker(new MarkerOptions().position(ZK).title("ZK Fitness"));

        LatLng Shfc = new LatLng(33.720462, 73.055522);
        mMap.addMarker(new MarkerOptions().position(Shfc).title("Smarts Health and Fitness Club"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FitnessZoneGym, 12.5f));




    }
}
