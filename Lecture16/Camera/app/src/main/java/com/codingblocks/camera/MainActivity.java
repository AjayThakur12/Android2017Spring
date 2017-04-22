package com.codingblocks.camera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "CAM";

    SurfaceView svCamera;
    Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        svCamera = (SurfaceView) findViewById(R.id.surfaceViewCamera);

        int camPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if (camPerm != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    231);
        } else {
            checkCameraSizes();
        }

        camera = Camera.open();

    }

    void checkCameraSizes () {
        Camera.Parameters camParams = camera.getParameters();
        for (Camera.Size picSize : camParams.getSupportedPictureSizes()) {
            Log.d(TAG, "picSize: " + picSize.width + " " + picSize.height);
        }
        for (Camera.Size prevSize : camParams.getSupportedPreviewSizes()) {
            Log.d(TAG, "picSize: " + prevSize.width + " " + prevSize.height);
        }
        for (Camera.Size vidSize : camParams.getSupportedVideoSizes()) {
            Log.d(TAG, "picSize: " + vidSize.width + " " + vidSize.height);
        }
    }

    @Override
    protected void onDestroy() {
        if (camera != null) {
            camera.release();
        }
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 231) {
            if (permissions[0].equals(Manifest.permission.CAMERA)) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkCameraSizes();
                }
            } else {
                Toast.makeText(this, "You did not give permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
