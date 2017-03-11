package com.codingblocks.listviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvCourses;
    ArrayList<Course> courseList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courseList.add(new Course("Launchpad", "Prateek"));
        courseList.add(new Course("Crux", "Sumeet"));
        courseList.add(new Course("Pandora", "Arnav"));
        courseList.add(new Course("Elixir", "Arnav"));

        lvCourses = (ListView) findViewById(R.id.lvCourses);

        CourseListAdapter clAdapter = new CourseListAdapter(courseList, this);

        lvCourses.setAdapter(clAdapter);

    }
}
