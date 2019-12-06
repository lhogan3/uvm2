package com.lhogan.uvm2;

//import necessary libraries
import android.os.AsyncTask;
import android.util.Log;

import com.lbelivea.uvm2.CourseListActivity;
import com.lbelivea.uvm2.MainActivity;
import com.lbelivea.uvm2.MyCourseListActivity;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.lbelivea.uvm2.CourseListActivity.SimpleItemRecyclerViewAdapter.resetAdapter;
import static com.lbelivea.uvm2.LoggedInUser.findUserCourses;

//Defining the CourseContent Class
public class CourseContent {

    //Async class for scraping courses from the UVM website.
    public static class Scraping extends AsyncTask<Void, Void, CourseContent.Course> {

        //Overriding the doInBackground function.
        @Override
        protected CourseContent.Course doInBackground(Void... params) {

            //crnTable for keeping track of the CRNs that have already been added to the list.
            boolean[] crnTable = new boolean[19999];

            //Clear all of the lists.
            clearLists();

            try {
                //URL to where the .txt file is being hosted by the UVM Registrar.
                URL url = new URL("https://giraffe.uvm.edu/~rgweb/batch/curr_enroll_spring.txt");

                //Open a scanner to read through the classes.
                Scanner sc = new Scanner(url.openStream());
                sc.nextLine();

                //Loop through the file line by line, and scrub the text to be in the format that we want.
                while (sc.hasNextLine()) {
                    String currentLine = sc.nextLine().replace("\"", "");
                    String current[] = currentLine.split(",");

                    //If the CRN read on a certain line is not in the crnTable, then add it to the crnTable.
                    if (!crnTable[Integer.parseInt(current[3])]) {
                        crnTable[Integer.parseInt(current[3])] = true;

                        //Determining the constructor to use to create the Course object.
                        if(current.length == 20) {
                            //This constructor is for when the class in the txt has everything that it is supposed to.
                            CourseContent.addItem(new Course(current[0], current[1], current[2], current[3], current[4], current[5], current[6], current[7], current[8], current[9], current[10], current[11], current[12], current[13], current[14], current[15], current[17] + " " + current[16], current[18], current[19]));
                        }
                        else if(current.length == 19){
                            //This constructor is for when the instructor is listed as STAFF.
                            CourseContent.addItem(new Course(current[0], current[1], current[2], current[3], current[4], current[5], current[6], current[7], current[8], current[9], current[10], current[11], current[12], current[13], current[14], current[15], current[17] + " " + current[16], current[18]));
                        }
                        else{
                            //Some classes are missing many different things. Currently, just calling the default constructor.
                            CourseContent.addItem(new Course());
                        }
                    }
                }

                //If it is the first time through, then find the UserCourses
                if (MainActivity.firstTimeThrough) {
                    findUserCourses();
                    MainActivity.firstTimeThrough = false;
                }
            } catch (IOException e) {
                Log.d("ERROR", "doInBackground: IO ERROR");
            }
            return new CourseContent.Course();
        }

        //Reset the adapter after this Async Task Completes
        @Override
        protected void onPostExecute(CourseContent.Course lastCourse) {
            if (CourseListActivity.getmAdapter() != null || MyCourseListActivity.getmAdapter() != null) {
                resetAdapter();
            }
        }
    }

    //Courses to be returned to the Recycler View.
    public static List<Course> COURSES = new ArrayList<>();

    //Courses being added to a map.
    public static Map<String, Course> COURSE_MAP = new HashMap<>();

    //Adding the course to both the List and the Map
    public static void addItem(Course course) {
        COURSES.add(course);
        COURSE_MAP.put(course.CRN, course);
    }

    //Clears both the List and the Map.
    public static void clearLists(){
        COURSES.clear();
        COURSE_MAP.clear();
    }

    //Defining the Course class
    public static class Course {

        //Fields for a course (given by the headers in the .txt file from the registrar
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

        //Constructor that will set all of the fields.
        public Course(String subject, String number, String name, String CRN, String section,
                      String lecLab, String campusCode, String collegeCode, String maxEnrollment,
                      String currentEnrollment, String startTime, String endTime, String days,
                      String credits, String building, String room, String instructor,
                      String netID, String email) {

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

        //Constructor for if the Instructor is listed as ".Staff" in the .txt
        public Course(String subject, String number, String name, String CRN, String section, String lecLab, String campusCode, String collegeCode, String maxEnrollment, String currentEnrollment, String startTime, String endTime, String days, String credits, String building, String room, String instructor, String netID) {

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
        }

        //Default Constructor
        public Course() { }

        //Overriding the toString Method to allow us to extend this class.
        @Override
        public String toString() {
            return subject + " " + number + " " + section + " " + name;
        }

        //Get all of the data from a Course.
        public String getData(){
            return subject + " " + number + " " + name + " " + startTime + " " + endTime + " "
                    + days + " " + instructor;
        }

        //Getter for the Course CRN
        public String getCRN() {
            return CRN;
        }
    }
}
