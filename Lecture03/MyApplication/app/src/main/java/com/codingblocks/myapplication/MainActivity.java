package com.codingblocks.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText etNum1, etNum2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int a = Integer.valueOf(etNum1.getText().toString());
                int b = Integer.valueOf(etNum2.getText().toString());
                int c = a+b;

                Intent i = new Intent(MainActivity.this, AnotherActivity.class);

                i.putExtra("mydata", c);

                startActivity(i);

            }
        });

    }
}
