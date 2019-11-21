package com.lbelivea.uvm2;

import com.lhogan.uvm2.CourseContent;

import java.util.ArrayList;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    private ArrayList<CourseContent.Course> userCourses;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
        userCourses = new ArrayList<>();
    }

    String getDisplayName() {
        return displayName;
    }
}
