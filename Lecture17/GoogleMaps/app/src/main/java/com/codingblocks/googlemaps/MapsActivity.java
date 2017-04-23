package com.codingblocks.googlemaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        btnChange = (Button) findViewById(R.id.btnChangeMap);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng newLocation = new LatLng(16.456, 83.555);
                MarkerOptions newMarker = new MarkerOptions();

                newMarker.position(newLocation);
                newMarker.title("Some Place we want to go");

                mMap.addMarker(newMarker);
                CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(newLocation, 10);
                mMap.animateCamera(cu);
            }
        });
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

        LatLng delhiLocation = new LatLng(23.324, 77.234);
        MarkerOptions delhiMarker = new MarkerOptions();

        delhiMarker.position(delhiLocation);
        delhiMarker.title("New Delhi");

        mMap.addMarker(delhiMarker);
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(delhiLocation, 5);
        mMap.moveCamera(cu);

        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);



    }
}
