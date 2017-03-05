package com.codingblocks.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        int myData = getIntent().getIntExtra("mydata", 0);

        tv = (TextView) findViewById(R.id.textView2);

        tv.setText(String.valueOf(myData));
    }
}
