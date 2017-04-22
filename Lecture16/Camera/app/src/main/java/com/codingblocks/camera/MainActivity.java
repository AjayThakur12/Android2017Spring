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
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "CAM";

    SurfaceView svCamera;
    SurfaceHolder surfaceHolder;
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

    }

    void checkCameraSizes () {
        camera = Camera.open();

        final Camera.Parameters camParams = camera.getParameters();
        for (Camera.Size picSize : camParams.getSupportedPictureSizes()) {
            Log.d(TAG, "picSize: " + picSize.width + " " + picSize.height);
        }
        for (Camera.Size prevSize : camParams.getSupportedPreviewSizes()) {
            Log.d(TAG, "prevSize: " + prevSize.width + " " + prevSize.height);
        }
        for (Camera.Size vidSize : camParams.getSupportedVideoSizes()) {
            Log.d(TAG, "vidSize: " + vidSize.width + " " + vidSize.height);
        }

        surfaceHolder = svCamera.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    camera.setPreviewDisplay(surfaceHolder);
                    camera.setDisplayOrientation(90);
                    camera.startPreview();
                } catch (IOException e) {
                    Log.d(TAG, "surfaceCreated: Could not start preview" );
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                WindowManager winMan = (WindowManager) getSystemService(WINDOW_SERVICE);
                Display display = winMan.getDefaultDisplay();

                if (holder.getSurface() == null) {
                    return;
                }

                try {
                    camera.stopPreview();
                } catch (Exception e) {
                    Log.e(TAG, "surfaceChanged: ", e);
                }

                Camera.Parameters changedParams = camera.getParameters();

                if (display.getRotation() == Surface.ROTATION_0) {
                    camera.setDisplayOrientation(90);

                }
                if (display.getRotation() == Surface.ROTATION_90) {
                    camera.setDisplayOrientation(0);

                }
                if (display.getRotation() == Surface.ROTATION_180) {
                    camera.setDisplayOrientation(270);


                }
                if (display.getRotation() == Surface.ROTATION_270) {
                    camera.setDisplayOrientation(180);
                }


                try {
                    camera.setParameters(changedParams);
                    camera.setPreviewDisplay(holder);
                    camera.startPreview();
                } catch (Exception e) {
                    Log.e(TAG, "surfaceChanged: ", e);
                }



            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }

    @Override
    protected void onPause() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (camera != null) {
            try {
                camera.reconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
