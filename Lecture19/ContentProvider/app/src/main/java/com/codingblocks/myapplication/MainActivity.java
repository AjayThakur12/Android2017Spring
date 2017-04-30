package com.codingblocks.myapplication;

import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor dataCursor = getContentResolver().query(
                Uri.parse(MyProviderContract.CONTENT_URI),
                new String[]{
                        MyProviderContract.Columns.ID,
                        MyProviderContract.Columns.NAME
                },
                null,
                null,
                null
        );

        while (dataCursor.moveToNext()) {

        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

            Cursor smsCursor = getContentResolver().query(
                    Telephony.Sms.CONTENT_URI,
                    new String[] {
                            Telephony.TextBasedSmsColumns.DATE_SENT,
                            Telephony.TextBasedSmsColumns.ADDRESS,
                            Telephony.TextBasedSmsColumns.BODY
                    },
                    "ADDRESS = ?",
                    new String[] {
                            "121"
                    },
                    null
            );
        }
    }
}
