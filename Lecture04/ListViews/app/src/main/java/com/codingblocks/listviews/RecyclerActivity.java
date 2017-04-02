package com.codingblocks.listviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    public static final String TAG = "DP";

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

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float widthDp = displayMetrics.widthPixels / displayMetrics.density;
        Log.d(TAG, "onCreate: " + widthDp);
        int columns = widthDp > 500 ? 2 : 1;

        //courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseRecyclerView.setLayoutManager(new GridLayoutManager(this, columns));

        courseRecyclerView.setAdapter(crAdapter);

    }
}
