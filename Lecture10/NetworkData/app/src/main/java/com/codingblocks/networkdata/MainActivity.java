package com.codingblocks.networkdata;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "NET";

    Button btnDownload;
    TextView resultView;
    String myURL = "http://cb.lk/admin";

    class DownloadTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            URL url = null;
            String result = null;
            try {
                url = new URL(myURL);
            } catch (MalformedURLException e) {

            }

            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String buf = "";
                while (buf != null) {
                    buf = br.readLine();
                    sb.append(buf);
                }
                result = sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            resultView.setText(s);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownload = (Button) findViewById(R.id.btnDownload);
        resultView = (TextView) findViewById(R.id.tvResult);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMan =
                        (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

                NetworkInfo[] networks  = connMan.getAllNetworkInfo();
                for (NetworkInfo netInfo : networks) {
                    Log.d(TAG, "onClick: " + netInfo.getTypeName());
                    Log.d(TAG, "onClick: " + netInfo.getSubtypeName());
                    Log.d(TAG, "onClick: " + netInfo.getExtraInfo());
                    Log.d(TAG, "onClick: " + netInfo.getState());
                }
                NetworkInfo activeNetInfo = connMan.getActiveNetworkInfo();

                if (activeNetInfo != null && activeNetInfo.getState() == NetworkInfo.State.CONNECTED) {
                    DownloadTask dTask = new DownloadTask();
                    dTask.execute();
                } else {
                    Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
