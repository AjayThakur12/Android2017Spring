package com.condingblocks.services;

import android.content.Intent;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String  TAG = "MainActivity";
    Button startBtn;
    Button uploadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.button);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
////                loopWithTen(10);
//                Intent i = new Intent(MainActivity.this , ThreadService.class);
//                i.setAction(ThreadService.ACTION_DOWNLOAD);
//                i.putExtra("url" , "http://url");
////                i.putExtra("helloo" , "world");
//                startService(i);

                ThreadService.setIntent(MainActivity.this , ThreadService.ACTION_DOWNLOAD , "http://");

            }
        });

        uploadBtn = (Button) findViewById(R.id.button2);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThreadService.setIntent(MainActivity.this , ThreadService.ACTION_UPLOAD , "http://");
            }
        });

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
