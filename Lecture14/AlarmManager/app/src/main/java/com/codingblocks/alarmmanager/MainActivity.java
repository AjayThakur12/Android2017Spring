package com.codingblocks.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        PendingIntent pi = PendingIntent.getActivity(
                this,
                111,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT
        );


        am.setInexactRepeating(
                AlarmManager.RTC,
                SystemClock.elapsedRealtime() + 5000,
                10000,
                pi

        );

    }
}
