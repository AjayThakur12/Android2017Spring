package com.codingblocks.listviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by championswimmer on 12/03/17.
 */

public class CourseRecycleAdapter extends
        RecyclerView.Adapter<CourseRecycleAdapter.CourseItemHolder> {

    private ArrayList<Course> courses;
    private Context context;

    public CourseRecycleAdapter(ArrayList<Course> courses, Context context) {
        this.courses = courses;
        this.context = context;
    }



    @Override
    public CourseItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layoutType;
        switch (viewType) {
            case 0: layoutType = R.layout.list_item_course_details; break;
            case 1: layoutType = R.layout.list_item_course_details_center; break;
            case 2:
            default: layoutType = R.layout.list_item_course_details_right; break;
        }
        View itemView = li.inflate(layoutType, parent, false);

        return new CourseItemHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {

        Course thisCourse = courses.get(position);

        if (thisCourse.getCourseName().equals("Pandora")) {
            return 0;
        }
        if (thisCourse.getCourseName().equals("Elixir")) {
            return 1;
        }

        return 2;
    }

    @Override
    public void onBindViewHolder(CourseItemHolder holder, int position) {
        Course thisCourse = courses.get(position);
        holder.tvCourseName.setText(thisCourse.getCourseName());
        holder.tvTeacherName.setText(thisCourse.getTeacherName());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class CourseItemHolder extends RecyclerView.ViewHolder {
        TextView tvCourseName;
        TextView tvTeacherName;

        public CourseItemHolder(View itemView) {
            super(itemView);

            tvCourseName = (TextView) itemView.findViewById(R.id.tvCourseName);
            tvTeacherName = (TextView) itemView.findViewById(R.id.tvTeacherName);

        }
    }
}
