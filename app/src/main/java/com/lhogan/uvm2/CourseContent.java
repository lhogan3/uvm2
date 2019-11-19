package com.lhogan.uvm2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.lbelivea.uvm2.CourseListActivity.SimpleItemRecyclerViewAdapter.resetAdapter;


public class CourseContent {
    private static class Scraping extends AsyncTask<Void, Void, CourseContent.Course> {
        @Override
        protected CourseContent.Course doInBackground(Void... params) {
            try {
                URL url = new URL("https://giraffe.uvm.edu/~rgweb/batch/curr_enroll_spring.txt");
                Scanner sc = new Scanner(url.openStream());
                sc.nextLine();
                while (sc.hasNextLine()) {
                    String currentLine = sc.nextLine();
                    currentLine = currentLine.replace("\"", "");
                    String current[] = currentLine.split(",");
                    try {
                        CourseContent.addItem(new CourseContent.Course(current[0], current[1], current[2], current[3], current[4], current[5], current[6], current[7], current[8], current[9], current[10], current[11], current[12], current[13], current[14] + " " + current[15], current[16], current[17]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Log.d("ERROR", "Staff Error");
                    }
                }
            } catch (IOException e) {
                Log.d("ERROR", "doInBackground: IO ERROR");
            }
            return new CourseContent.Course();
        }

        @Override
        protected void onPostExecute(CourseContent.Course lastCourse) {
            resetAdapter();
        }
    }
    /**
     * An array of sample (dummy) items.
     */
    public static final List<Course> COURSES = new ArrayList<Course>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Course> COURSE_MAP = new HashMap<String, Course>();

    static {
        new Scraping().execute();
    }

    public static void addItem(Course course) {
        COURSES.add(course);
        COURSE_MAP.put(course.CRN, course);
    }



    /**
     * A dummy item representing a piece of content.
     */
    public static class Course {
        public String subject;
        public String number;
        public String name;
        public String CRN;
        public String section;
        public String lecLab;
        public String campusCode;
        public String collegeCode;
        public String maxEnrollment;
        public String currentEnrollment;
        public String startTime;
        public String endTime;
        public String days;
        public String credits;
        public String building;
        public String room;
        public String instructor;
        public String netID;
        public String email;

        public Course(String subject, String number, String name, String CRN, String section, String lecLab, String campusCode, String collegeCode, String maxEnrollment, String currentEnrollment, String startTime, String endTime, String days, String credits, String building, String room, String instructor, String netID, String email) {
            this.subject = subject;
            this.number = number;
            this.name = name;
            this.CRN = CRN;
            this.section = section;
            this.lecLab = lecLab;
            this.campusCode = campusCode;
            this.collegeCode = collegeCode;
            this.maxEnrollment = maxEnrollment;
            this.currentEnrollment = currentEnrollment;
            this.startTime = startTime;
            this.endTime = endTime;
            this.days = days;
            this.credits = credits;
            this.building = building;
            this.room = room;
            this.instructor = instructor;
            this.netID = netID;
            this.email = email;
        }

        public Course() {
            // hi there :) hey :) howdy :)
        }

        public Course(String subject, String number, String name, String CRN, String section, String lecLab, String campusCode, String collegeCode, String maxEnrollment, String currentEnrollment, String startTime, String endTime, String days, String credits, String instructor, String netID, String email) {
            this.subject = subject;
            this.number = number;
            this.name = name;
            this.CRN = CRN;
            this.section = section;
            this.lecLab = lecLab;
            this.campusCode = campusCode;
            this.collegeCode = collegeCode;
            this.maxEnrollment = maxEnrollment;
            this.currentEnrollment = currentEnrollment;
            this.startTime = startTime;
            this.endTime = endTime;
            this.days = days;
            this.credits = credits;
            this.instructor = instructor;
            this.netID = netID;
            this.email = email;
        }

        @Override
        public String toString() {

            return name;
        }

        /**
         * concatenates all content for filtering
         * @return all course content in one string, separated by spaces
         */
        public String getData(){
            return subject + " " + number + " " + name + " " + startTime + " " + endTime + " "
                    + days + " " + instructor;
        }
    }
}
