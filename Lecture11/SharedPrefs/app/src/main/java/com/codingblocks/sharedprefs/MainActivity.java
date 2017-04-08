package com.codingblocks.sharedprefs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MY_PREF = "MY_PREF";
    public static final String KEY_RESULT = "result";

    Button btnAdd;
    TextView tvResult;
    EditText etVar1, etVar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences sPref = getSharedPreferences(MY_PREF, MODE_PRIVATE);


        etVar1 = (EditText) findViewById(R.id.etVar1);
        etVar2 = (EditText) findViewById(R.id.etVar2);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        tvResult.setText(String.valueOf(sPref.getInt(KEY_RESULT, 0)));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = Integer.valueOf(etVar1.getText().toString())
                        +
                        Integer.valueOf(etVar2.getText().toString());

                tvResult.setText(
                        String.valueOf(result)
                );

                SharedPreferences.Editor prefEditor = sPref.edit();
                prefEditor.putInt(KEY_RESULT, result);
                //add or remove more keys
                prefEditor.apply();

            }
        });


    }
}
