package com.codingblocks.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> cbCourses = new ArrayList<>();


    ListView lvCourses;
    Button btnAdd;
    EditText etNewCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbCourses.add("Launchpad");
        cbCourses.add("Crux");
        cbCourses.add("Pandora");
        cbCourses.add("Elixir");

        int n = 35;
        ArrayList<String> fbArr = new ArrayList<>(35);
        for (int i = 1; i <= 35; i++) {
            String element = "";
            if (i%3 == 0) element += "fizz";

            if (i%5 == 0) element += "buzz";

            if (element.isEmpty()) element = String.valueOf(i);

            fbArr.add(element);

        }



        newArr.addAll(fbArr.subList(4,10));

        lvCourses = (ListView) findViewById(R.id.listCourses);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        etNewCourse = (EditText) findViewById(R.id.etNewCourse);
        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                cbCourses
        );

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbCourses.add(etNewCourse.getText().toString());
                myAdapter.notifyDataSetChanged();
            }
        });

        lvCourses.setAdapter(myAdapter);

        createArray(10, String.class);
        createArray(10, Integer.class);


    }

    public <T> ArrayList<T> createArray (int N, Class<?> T) {
        ArrayList<T> ai = new ArrayList<>(N);
        //addd
        return ai;
    }
}
