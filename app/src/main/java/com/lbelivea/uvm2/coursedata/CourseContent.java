package com.lbelivea.uvm2.coursedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CourseContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<CourseItem> ITEMS = new ArrayList<CourseItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, CourseItem> ITEM_MAP = new HashMap<String, CourseItem>();

    private static final int COUNT = 9;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createCourseItem(i));
        }
    }

    private static void addItem(CourseItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.CRN, item);
    }

    //need to update this with Liam's scraping code
    private static CourseItem createCourseItem(int position) {
        return new CourseItem(String.valueOf(position), "Course " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        //builder.append("Course ").append(position);
        builder.append("\nCourse details go here.");

        return builder.toString();
    }

    /**
     * An item representing a piece of content.
     */
    public static class CourseItem {
        public final String subject, number, name, CRN, section, lecLab, campusCode, collegeCode, maxEnrollment, currentEnrollment, startTime, endTime, days, credits, building, room, instructor, netID, email;

        public CourseItem(String subject, String number, String name, String CRN, String section, String lecLab, String campusCode, String collegeCode, String maxEnrollment, String currentEnrollment, String startTime, String endTime, String days, String credits, String building, String room, String instructor, String netID, String email;) {
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

        @Override
        public String toString() {
            return content;
        }
    }
}
