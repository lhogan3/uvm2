package com.lhogan.uvm2;

import android.util.Log;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CourseContent {
    /**
     * An array of sample (dummy) items.
     */
    public static final List<Course> COURSES = new ArrayList<Course>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Course> COURSE_MAP = new HashMap<String, Course>();

    private static final int COUNT = 9;

    static {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Log.d("asf", "Async thread starting");
                    scraping();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void addItem(Course course) {
        COURSES.add(course);
        COURSE_MAP.put(course.CRN, course);
    }

    private static void scraping() throws Exception {
        URL url = new URL("https://giraffe.uvm.edu/~rgweb/batch/curr_enroll_spring.txt");
        Scanner sc = new Scanner(url.openStream());
        String[] titles = sc.nextLine().split(",");
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            currentLine = currentLine.replace("\"", "");
            String current[] = currentLine.split(",");
//            if (titles.length == 17) {
            Log.d("asdfasdfasdf", "firstcheckpoint");
                try {
                    Log.d("heythere", "secondcheckpoint");
                    addItem(new Course(current[0], current[1], current[2], current[3], current[4], current[5], current[6], current[7], current[8], current[9], current[10], current[11], current[12], current[13], current[14] + " " + current[15], current[16], current[17]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Staff Error");
                }
//            } else {
//                try {
//                    addItem(new Course(current[0], current[1], current[2], current[3], current[4], current[5], current[6], current[7], current[8], current[9], current[10], current[11], current[12], current[13], current[14], current[15], current[16] + " " + current[17], current[18], current[19]));
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println("Staff Error");
//                }

        //    }
        }
        Log.d("heythere", "made it to the end");
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
    }
}
