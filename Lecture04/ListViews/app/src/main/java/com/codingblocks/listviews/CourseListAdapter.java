package com.codingblocks.listviews;

import android.content.Context;
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

    private ArrayList<Course> courses;
    private Context context;

    public CourseListAdapter(ArrayList<Course> courses, Context context) {
        this.courses = courses;
        this.context = context;
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Course getItem(int position) {
        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = li.inflate(R.layout.list_item_course_details, null);

        TextView tvCourseName = (TextView) rootView.findViewById(R.id.tvCourseName);
        TextView tvTeacherName = (TextView) rootView.findViewById(R.id.tvTeacherName);

        Course thisCourse = getItem(position);
        tvCourseName.setText(thisCourse.getCourseName());
        tvTeacherName.setText(thisCourse.getTeacherName());

        return rootView;
    }
}
