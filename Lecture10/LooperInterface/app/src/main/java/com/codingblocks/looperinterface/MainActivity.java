package com.codingblocks.looperinterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvCounter;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        tvCounter = (TextView) findViewById(R.id.tvCounter);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timer t = new Timer();
                t.setOnTickListener(new Timer.OnTickListener() {
                    @Override
                    public void onTick(int secPassed) {
                        tvCounter.setText(String.valueOf(secPassed));
                    }
                });
                t.execute(10);
            }
        });

    }
}
