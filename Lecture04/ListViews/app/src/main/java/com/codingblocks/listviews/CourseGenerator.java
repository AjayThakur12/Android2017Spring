package com.codingblocks.listviews;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by championswimmer on 11/03/17.
 */

public class CourseGenerator {

    static String[] courses = new String[] {
            "Launchpad", "Crux", "Pandora", "Elixir",
            "Django", "Perceptron", "GameDev"
    };

    static String[] teachers = new String[] {
            "Prateek", "Sumeet", "Arnav", "Rishab",
            "Deepak", "Shubham"
    };

    static Random r = new Random();

    static Course genRandom() {
        int c, t;
        c = r.nextInt(courses.length);
        t = r.nextInt(teachers.length);
        return new Course(courses[c], teachers[t]);
    }

    static ArrayList<Course> getRandomList(int n) {
        ArrayList<Course> courses = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            courses.add(genRandom());
        }
        return courses;
    }
}
