package com.lbelivea.uvm2;

import com.lhogan.uvm2.CourseContent;

import java.util.ArrayList;


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
        // add the course to the user's course list
        courses.add(course);
    }

    public void deleteCourse(String CRN){
        // marked course for deletion
        CourseContent.Course markedCourse = new CourseContent.Course();

        // loop through user courses
        for (CourseContent.Course c: courses) {
            // fir the CRNs are the same
            if(c.CRN.equals(CRN)){
                // mark for deletion
                markedCourse = c;
            }
        }
        // remove from user's course list
        courses.remove(markedCourse);
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
        // loop through all courses
        for (int i = 0; i < CourseContent.COURSES.size(); i++) {
            // for the user's CRNs
            for (int j = 0; j < crns.size(); j++) {
                // if the crn in CRN equals the crn of a course
                if (crns.get(j).equals(CourseContent.COURSES.get(i).CRN)) {
                    // add to user's courses
                    courses.add(CourseContent.COURSES.get(i));
                }
            }
        }
    }

    public static void parseCourses(String APIResponse) {
        // where to start the response
        int startIndex = APIResponse.indexOf("[") + 1;

        // where to end the response
        int endIndex = APIResponse.indexOf("]");

        // substring from start to end
        APIResponse = APIResponse.substring(startIndex, endIndex);

        // replace " with empty string
        APIResponse = APIResponse.replace("\"", "");

        // split on the commas
        String crnArray[] = APIResponse.split(",");

        // add the crns to the crnArray
        for (int i = 0; i < crnArray.length; i++) {
            crns.add(crnArray[i]);
        }
    }
}
