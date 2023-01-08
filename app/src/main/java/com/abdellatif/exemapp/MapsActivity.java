package com.abdellatif.exemapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.abdellatif.exemapp.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        // Add a marker to 4 random locations and move the camera
        LatLng cr1 = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(cr1).title("Marker in cooperative 1"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cr1));

        LatLng cr2 = new LatLng(30.0444, 31.2357);
        mMap.addMarker(new MarkerOptions().position(cr2).title("Marker in cooperative 2"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cr2));

        LatLng cr3 = new LatLng(48.8566, 2.3522);
        mMap.addMarker(new MarkerOptions().position(cr3).title("Marker in cooperative 3"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cr3));

        LatLng cr4 = new LatLng(40.7128, 10);
        mMap.addMarker(new MarkerOptions().position(cr4).title("Marker in cooperative 4"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cr4));

        LatLng cr5 = new LatLng(40.7128, 17);
        mMap.addMarker(new MarkerOptions().position(cr5).title("Marker in cooperative 5"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cr5));

        LatLng taroudant = new LatLng(30.4727, 8.8749);
        mMap.addMarker(new MarkerOptions().position(taroudant).title("Marker in tarouadnt"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(taroudant));


        //display the map
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}