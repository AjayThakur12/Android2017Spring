package com.codingblocks.filestorage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btnAdd;
    TextView textView;

    public static final String TAG = "FILES";
    public static final String MY_FILE_NAME = "myfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "FilesDir: " + getFilesDir().getPath());
        Log.d(TAG, "CacheDir: " + getCacheDir().getPath());
        try {
            Log.d(TAG, "ExtCacheDir: " + getExternalCacheDir().getPath());
            Log.d(TAG, "ExtFilesDir: " + getExternalFilesDir(null).getPath());
        } catch (Exception e) {
            Log.e(TAG, "onCreate: Could not fetch external directory", e);
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            File[] extFilesDirs = getExternalFilesDirs(Environment.DIRECTORY_MUSIC);
            for (File f: extFilesDirs) {
                Log.d(TAG, "ExtFilesDir: " + f.getPath());
            }
        }

        Log.d(TAG, "EXT_STOR_DIR: " + Environment.getExternalStorageDirectory());
        Log.d(TAG, "EXT_MOVIES_DIR: " +
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES));

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAddFile);

        textView.setText(readFromFile(MY_FILE_NAME));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToFile(MY_FILE_NAME, editText.getText().toString());
            }
        });


    }

     void writeToFile(String fileName, String data) {

         File f = new File(getFilesDir(), fileName);
         if (!f.exists()) {
             try {
                 f.createNewFile();
             } catch (IOException e) {
                 Log.e(TAG, "writeToFile: Could not create file", e);
             }
         }
         FileOutputStream fos = null;

         try {
             fos = new FileOutputStream(f, true);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
             return;
         }

         try {
             fos.write(data.getBytes());
         } catch (IOException e) {
             e.printStackTrace();
         }


     }

    String readFromFile(String fileName) {
        File f = new File(getFilesDir(), fileName);
        if (!f.exists()) {
            Log.w(TAG, "readFromFile: File " + f.getPath() + " does not exist");
            return "";
        }

        String data = "";

        try {
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String buf = "";
            while((buf = br.readLine()) != null) {
                sb.append(buf);
            }
            data = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
