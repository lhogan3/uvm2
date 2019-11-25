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
    private static ArrayList<String> crns;

    public LoggedInUser(String netId, String password) {
        this.netId = netId;
        this.password = password;
        this.loggedIn = true;
        courses = new ArrayList<>();
        crns = new ArrayList<>();
    }

    public LoggedInUser(boolean bad) {
        this.netId = "";
        this.password = "";
        this.loggedIn = false;
        courses = new ArrayList<>();
        crns = new ArrayList<>();
    }

    public void addCourse(CourseContent.Course course) {
        courses.add(course);
    }

    public void deleteCourse(String CRN){
        for (CourseContent.Course c: courses) {
            if(c.CRN.equals(CRN)){
                courses.remove(c);
            }
        }
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

    public static void findUserCourses() {
        for (int i = 0; i < CourseContent.TEMP_COURSES.size(); i++) {
            for (int j = 0; j < crns.size(); j++) {
                if (crns.get(j).equals(CourseContent.TEMP_COURSES.get(i).CRN)) {
                    courses.add(CourseContent.TEMP_COURSES.get(i));
                }
            }
        }
    }

    public static void parseCourses(String APIResponse) {
        int startIndex = APIResponse.indexOf("[") + 1;
        int endIndex = APIResponse.indexOf("]");

        APIResponse = APIResponse.substring(startIndex, endIndex);

        APIResponse = APIResponse.replace("\"", "");

        String crnArray[] = APIResponse.split(",");

        for (int i = 0; i < crnArray.length; i++) {
            crns.add(crnArray[i]);
        }
    }
}
