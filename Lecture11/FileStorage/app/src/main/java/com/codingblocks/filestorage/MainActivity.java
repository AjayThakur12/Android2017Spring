package com.codingblocks.filestorage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "FILES";

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


    }
}
