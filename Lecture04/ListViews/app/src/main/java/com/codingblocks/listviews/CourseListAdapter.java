package com.codingblocks.listviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by championswimmer on 11/03/17.
 */

public class CourseListAdapter extends BaseAdapter {
    public static final String TAG = "CLA";

    private ArrayList<Course> courses;
    private Context context;

    public CourseListAdapter(ArrayList<Course> courses, Context context) {
        this.courses = courses;
        this.context = context;
    }

    @Override
    public int getCount() {
        //Log.d(TAG, "getCount: ");
        return courses.size();
    }

    @Override
    public Course getItem(int position) {
        //Log.d(TAG, "getItem: " + position);
        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Log.d(TAG, "getItemId: ");
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: " + position + convertView);
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = li.inflate(R.layout.list_item_course_details, null);
        } else {

        }


        TextView tvCourseName = (TextView) convertView.findViewById(R.id.tvCourseName);
        TextView tvTeacherName = (TextView) convertView.findViewById(R.id.tvTeacherName);

        Course thisCourse = getItem(position);
        tvCourseName.setText(thisCourse.getCourseName());
        tvTeacherName.setText(thisCourse.getTeacherName());

        return convertView;
    }
}
