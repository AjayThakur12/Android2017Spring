package com.codingblocks.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout mainLayout;
//    BroadcastReceiver myReceiver;
    ChargerConnectReceiver myChargerReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (RelativeLayout) findViewById(R.id.activity_main);

//        myReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
//                    mainLayout.setBackgroundColor(Color.GREEN);
//                }
//                if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
//                    mainLayout.setBackgroundColor(Color.RED);
//                }
//            }
//        };

        myChargerReceiver = new ChargerConnectReceiver(new ChargerConnectReceiver.OnChargerChangedListener() {
            @Override
            public void onConnected() {
                mainLayout.setBackgroundColor(Color.GREEN);
            }

            @Override
            public void onDisconnected() {
                mainLayout.setBackgroundColor(Color.RED);
            }
        });


        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myChargerReceiver, intentFilter);

    }

    @Override
    protected void onPause() {
        unregisterReceiver(myChargerReceiver);
        super.onPause();
    }
}
