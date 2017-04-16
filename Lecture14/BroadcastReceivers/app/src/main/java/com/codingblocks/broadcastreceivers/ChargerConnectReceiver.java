package com.codingblocks.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ChargerConnectReceiver extends BroadcastReceiver {

    public static final String TAG = "BR";

    public ChargerConnectReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            Log.d(TAG, "onReceive: POWER CONNECTED");
        }
        if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Log.d(TAG, "onReceive: POWER DISCONNECTED");
        }



    }
}
