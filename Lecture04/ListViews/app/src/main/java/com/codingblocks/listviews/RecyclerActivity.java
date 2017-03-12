package com.codingblocks.listviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    ArrayList<Course> courseList;
    RecyclerView courseRecyclerView;
    CourseRecycleAdapter crAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        courseList = CourseGenerator.getRandomList(200);

        courseRecyclerView = (RecyclerView) findViewById(R.id.rvCourseList);
        crAdapter = new CourseRecycleAdapter(courseList, this);

        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseRecyclerView.setAdapter(crAdapter);

    }
}
