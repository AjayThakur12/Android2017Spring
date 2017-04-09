package com.codingblocks.permissionmanager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAskForPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAskForPermission = (Button) findViewById(R.id.button);

        btnAskForPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(MainActivity.this,
//                        Manifest.permission.READ_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//
//
//                    ActivityCompat.requestPermissions(
//                            MainActivity.this,
//                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
//                            321);
//                }

                Perman.askForPermission(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        new Perman.OnPermissionRequestResult() {
                            @Override
                            public void onGranted(String permission) {

                            }

                            @Override
                            public void onDenied(String permission) {

                            }
                        });
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

//        if (requestCode == 321) {
//            for (int i = 0; i < permissions.length; i++) {
//                if (permissions[i] == Manifest.permission.READ_EXTERNAL_STORAGE) {
//                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                        // it has been granted
//                    }
//                }
//            }
//        }
        Perman.onPermissionResult(requestCode, permissions, grantResults);

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
