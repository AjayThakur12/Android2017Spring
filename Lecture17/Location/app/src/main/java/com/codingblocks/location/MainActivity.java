package com.codingblocks.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "LOC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int coarsePerm = ContextCompat
                .checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int finePerm = ContextCompat
                .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if ((finePerm != PackageManager.PERMISSION_GRANTED) ||
                (coarsePerm != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, 432);
        } else {
            getLocationUpdates();
        }


    }

    void getLocationUpdates() {
        LocationManager locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener locLis = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG, "onLocationChanged: alt " + location.getAltitude());
                Log.d(TAG, "onLocationChanged: lat " + location.getLatitude());
                Log.d(TAG, "onLocationChanged: lng" + location.getLongitude());
                Log.d(TAG, "onLocationChanged: brn" + location.getBearing());
                Log.d(TAG, "onLocationChanged: spd" + location.getSpeed());
                Log.d(TAG, "onLocationChanged: acc" + location.getAccuracy());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }


            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        //noinspection MissingPermission
        locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                20000, 100, locLis);

        //noinspection MissingPermission
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5000, 10, locLis);


        // Do this when you do not need further updates
        //locMan.removeUpdates(locLis);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 432) {
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "Permission not given", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            getLocationUpdates();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
