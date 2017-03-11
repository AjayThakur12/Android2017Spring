package com.codingblocks.listviews;

/**
 * Created by championswimmer on 11/03/17.
 */

public class Course {
    String courseName;
    String teacherName;

    public Course(String courseName, String teacherName) {
        this.courseName = courseName;
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
