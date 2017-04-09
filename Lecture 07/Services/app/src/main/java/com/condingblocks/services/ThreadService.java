package com.condingblocks.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by rishabhkhanna on 19/03/17.
 */

public class ThreadService extends IntentService {

    public static final String TAG = "service";


    public static final String ACTION_DOWNLOAD = "download";
    public static final String ACTION_UPLOAD = "upload";



    public ThreadService() {
        super("name");
    }


//
//    public void loopWithTen(int duration){
//        for(int i = 0 ; i < duration ; i++){
//            loopWithOne();
//        }
//
//    }
//
//    public void loopWithOne(){
//        long startTime = System.currentTimeMillis();
//        Log.d(TAG, "loopWithOne:  ");
//        while(System.currentTimeMillis() - startTime < 1000);
//
//    }
//

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");

        String action = intent.getAction();
        String url = intent.getStringExtra("url");

        if(action.equals(ACTION_DOWNLOAD)){
            onAction(ACTION_DOWNLOAD);
        }else if(action.equals(ACTION_UPLOAD)){
            onAction(ACTION_UPLOAD);
        }

//        loopWithTen(10);
    }


    public void onAction(String action ){
        if(action.equals(ACTION_DOWNLOAD)){

            // do download

        }else if (action.equals(ACTION_UPLOAD)){

            //do upload

        }

    }


    public static void setIntent(Context c , String action , String url){
        Intent i = new Intent(c , ThreadService.class);
        i.setAction(action);
        i.putExtra("url" , url);
        c.startService(i);
    }

}
