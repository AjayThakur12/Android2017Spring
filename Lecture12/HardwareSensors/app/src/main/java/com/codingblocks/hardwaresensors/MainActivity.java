package com.codingblocks.hardwaresensors;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "SM";

    RelativeLayout relativeLayout;
    SensorEventListener sel;
    SensorManager sensMan;
    Sensor accelSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);

        sensMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> mySensors = sensMan.getSensorList(Sensor.TYPE_ALL);

        accelSensor = sensMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        for (Sensor sensor : mySensors) {
            Log.d(TAG, "NAME: " + sensor.getName());
            Log.d(TAG, "NAME: " + sensor.getVendor());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                Log.d(TAG, "NAME: " + sensor.getStringType());
            }
        }

        sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.d(TAG, "onSensorChanged: ax" + event.values[0]);
                Log.d(TAG, "onSensorChanged: ay" + event.values[1]);
                Log.d(TAG, "onSensorChanged: az" + event.values[2]);
                Log.d(TAG, "onSensorChanged: =======================");

                relativeLayout.setBackgroundColor(Color.rgb(
                        accel2col(event.values[0]),
                        accel2col(event.values[1]),
                        accel2col(event.values[2])
                ));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };


    }

    int accel2col (float accel) {
        return (int) (((accel + 12) / 24) * 255);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensMan.registerListener(
                sel,
                accelSensor,
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        sensMan.unregisterListener(sel);

        super.onPause();
    }
}
