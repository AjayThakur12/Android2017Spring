package com.condingblocks.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public static final String  TAG = "service";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        loopWithLog(10);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void loopWithLog(int duration){
        long startTime = System.currentTimeMillis();

        for(int i  = 0 ; i < duration ; i++){
            loopWithOne();
            Log.d("TAG", "loopWithLog: ");
        }
    }

    public void loopWithOne(){
        long startTime = System.currentTimeMillis();

        while(System.currentTimeMillis() - startTime < 1000);
    }
}
