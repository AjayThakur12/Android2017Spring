package com.codingblocks.camera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Environment;
import android.os.SystemClock;
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
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "CAM";

    SurfaceView svCamera;
    SurfaceHolder surfaceHolder;
    Camera camera;
    Button btnTakePhoto;
    Camera.PictureCallback picCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        svCamera = (SurfaceView) findViewById(R.id.surfaceViewCamera);
        btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);


        int camPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if (camPerm != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    231);
        } else {
            checkCameraSizes();
        }

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int storePerm = ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (storePerm != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            222);
                } else {
                    capturePhoto();
                }
            }
        });

        picCallBack = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                String fileName = "photo-" + System.currentTimeMillis() + ".jpg";
                File myPhoto = new File(Environment.getExternalStorageDirectory(), fileName);

                try {
                    FileOutputStream fos = new FileOutputStream(myPhoto);
                    fos.write(data);
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    camera.startPreview();
                } catch (Exception e) {
                    Log.d(TAG, "onPictureTaken: Could not restart preview", e);
                }
            }
        };

    }

    void capturePhoto() {

        camera.takePicture(null, null, picCallBack);

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

        if (requestCode == 222) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    capturePhoto();
                }
            } else {
                Toast.makeText(this, "You did not give permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
