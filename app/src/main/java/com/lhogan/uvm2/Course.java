//package com.lhogan.uvm2;
//
//public class Course {
//    private String subject;
//    private String number;
//    private String name;
//    private String CRN;
//    private String section;
//    private String lecLab;
//    private String campusCode;
//    private String collegeCode;
//    private String maxEnrollment;
//    private String currentEnrollment;
//    private String startTime;
//    private String endTime;
//    private String days;
//    private String credits;
//    private String building;
//    private String room;
//    private String instructor;
//    private String netID;
//    private String email;
//
//    public Course(String subject, String number, String name, String CRN, String section, String lecLab, String campusCode, String collegeCode, String maxEnrollment, String currentEnrollment, String startTime, String endTime, String days, String credits, String building, String room, String instructor, String netID, String email) {
//        this.subject = subject;
//        this.number = number;
//        this.name = name;
//        this.CRN = CRN;
//        this.section = section;
//        this.lecLab = lecLab;
//        this.campusCode = campusCode;
//        this.collegeCode = collegeCode;
//        this.maxEnrollment = maxEnrollment;
//        this.currentEnrollment = currentEnrollment;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.days = days;
//        this.credits = credits;
//        this.building = building;
//        this.room = room;
//        this.instructor = instructor;
//        this.netID = netID;
//        this.email = email;
//    }
//
//    public Course(String subject, String number, String name, String CRN, String section, String lecLab, String campusCode, String collegeCode, String maxEnrollment, String currentEnrollment, String startTime, String endTime, String days, String credits, String instructor, String netID, String email) {
//        this.subject = subject;
//        this.number = number;
//        this.name = name;
//        this.CRN = CRN;
//        this.section = section;
//        this.lecLab = lecLab;
//        this.campusCode = campusCode;
//        this.collegeCode = collegeCode;
//        this.maxEnrollment = maxEnrollment;
//        this.currentEnrollment = currentEnrollment;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.days = days;
//        this.credits = credits;
//        this.instructor = instructor;
//        this.netID = netID;
//        this.email = email;
//    }
//
//    public String printer(){
//        return "Contents:\n\nSubject: " + subject + "\nNumber: " + number + "\nName: " + name + "\nCRN: " + CRN +
//                "\nSection: " + section + "\nLec/Lab: " + lecLab + "\nCampus Code: " + campusCode + "\nCollege Code: " + collegeCode +
//                "\nMax Enrollment: " + maxEnrollment + "\nCurrent Enrollment: " + currentEnrollment + "\nStart Time: " + startTime +
//                "\nEnd Time: " + endTime + "\nDays: " + days + "\nCredits: " + credits + "\nBuilding: " + building + "\nRoom: " + room +
//                "\nInstructor: " + instructor + "\nNetID: " + netID + "\nEmail: " + email + "\n----------\n";
//    }
//
//    public String getSubject() {
//        return subject;
//    }
//
//    public String getNumber() {
//        return number;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getCRN() {
//        return CRN;
//    }
//
//    public String getSection() {
//        return section;
//    }
//
//    public String getLecLab() {
//        return lecLab;
//    }
//
//    public String getCampusCode() {
//        return campusCode;
//    }
//
//    public String getCollegeCode() {
//        return collegeCode;
//    }
//
//    public String getMaxEnrollment() {
//        return maxEnrollment;
//    }
//
//    public String getCurrentEnrollment() {
//        return currentEnrollment;
//    }
//
//    public String getStartTime() {
//        return startTime;
//    }
//
//    public String getEndTime() {
//        return endTime;
//    }
//
//    public String getDays() {
//        return days;
//    }
//
//    public String getCredits() {
//        return credits;
//    }
//
//    public String getBuilding() {
//        return building;
//    }
//
//    public String getRoom() {
//        return room;
//    }
//
//    public String getInstructor() {
//        return instructor;
//    }
//
//    public String getNetID() {
//        return netID;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//}
