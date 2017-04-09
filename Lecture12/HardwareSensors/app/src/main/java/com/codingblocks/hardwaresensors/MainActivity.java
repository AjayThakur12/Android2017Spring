package com.codingblocks.hardwaresensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "SM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> mySensors = sensMan.getSensorList(Sensor.TYPE_ALL);

        Sensor accelSensor = sensMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        for (Sensor sensor : mySensors) {
            Log.d(TAG, "NAME: " + sensor.getName());
            Log.d(TAG, "NAME: " + sensor.getVendor());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                Log.d(TAG, "NAME: " + sensor.getStringType());
            }
        }

        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.d(TAG, "onSensorChanged: ax" + event.values[0]);
                Log.d(TAG, "onSensorChanged: ay" + event.values[1]);
                Log.d(TAG, "onSensorChanged: az" + event.values[2]);
                Log.d(TAG, "onSensorChanged: =======================");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensMan.registerListener(
                sel,
                accelSensor,
                SensorManager.SENSOR_DELAY_UI);
    }
}
