package com.condingblocks.async;

import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    Button btnStart;
    Button btnDestroy;
    TextView tvProgress;
    public long initialTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnDestroy = (Button) findViewById(R.id.btnDestroy);
        
        btnDestroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvProgress = (TextView) findViewById(R.id.tvProgress);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask myTask = new MyTask();
                myTask.execute(10);
//                for (int i = 0; i < 10 ; i++){
//                    oneLoop();
//                    Log.d(TAG, "onClick: MainThread");
//                }
            }
        });


    }

    public class MyTask extends AsyncTask<Integer , Float , String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Float... values) {
            Log.d(TAG, "onProgressUpdate: " + values[0]);
            tvProgress.setText(values[0].toString());
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Integer... params) {
            for(int i = 0 ;i < params[0] ; i++){
                initialTime = System.currentTimeMillis();
                oneLoop();
                Log.d(TAG, "doInBackground: " + i);

                if(i == 5){

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Main", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

//                publishProgress((float) i / (float) params[0]);
            }
            return "Completed";
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: ");
            super.onPostExecute(s);
        }
    }

    static void oneLoop(){
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime  < 1000);

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
