package com.codingblocks.listviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvCourses;
    ArrayList<Course> courseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courseList = CourseGenerator.getRandomList(300);

        lvCourses = (ListView) findViewById(R.id.lvCourses);

        CourseListAdapter clAdapter = new CourseListAdapter(courseList, this);

        lvCourses.setAdapter(clAdapter);

    }
}
