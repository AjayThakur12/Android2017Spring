package com.condingblocks.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    
    public static final String TAG = "Service";
    
    public MyService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent != null) {
            Log.d(TAG, "onStartCommand: " + intent.getStringExtra("hello"));
        }
        Log.d(TAG, "onStartCommand: ");
        loopWithTen(10);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public void loopWithTen(int duration){
        for(int i = 0 ; i < duration ; i++){
            loopWithOne();
        }

    }

    public void loopWithOne(){
        long startTime = System.currentTimeMillis();
        Log.d(TAG, "loopWithOne:  ");
        while(System.currentTimeMillis() - startTime < 1000);

    }
}
