package com.lbelivea.uvm2;

import com.lhogan.uvm2.CourseContent;

import java.util.ArrayList;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String netId;
    private String password;
    private boolean loggedIn;
    private static ArrayList<CourseContent.Course> courses;
    private static String APIResponse;

    public LoggedInUser(String netId, String password) {
        this.netId = netId;
        this.password = password;
        this.loggedIn = true;
        courses = new ArrayList<>();

        int startIndex = APIResponse.indexOf("[");
        int endIndex = APIResponse.indexOf("]");

        APIResponse = APIResponse.substring(startIndex, endIndex);

    }

    public LoggedInUser(boolean bad) {
        this.netId = "";
        this.password = "";
        this.loggedIn = false;
        courses = new ArrayList<>();
    }

    public void addCourse(CourseContent.Course course) {
        courses.add(course);
    }

    public String getNetId() {
        return netId;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public static ArrayList<CourseContent.Course> getCourses() {
        return courses;
    }

    public static void setAPIResponse(String APIResponde) {
        LoggedInUser.APIResponse = APIResponde;
    }
}
