package com.lbelivea.uvm2;

import android.os.AsyncTask;
import android.util.Log;

import com.lhogan.uvm2.CourseContent;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class ApiInteractions {
    public static class GetUser extends AsyncTask<String, Void, CourseContent.Course> {
        @Override
        protected CourseContent.Course doInBackground(String... params) {
            try {
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6969/getUser?netId=");

                URL.append(params[0]);

                URL.append("&password=");

                URL.append(params[1]);

                URL url = new URL(URL.toString());
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNextLine()) {
                    Log.d("API", "getUser: " + sc.nextLine());
                }
            } catch (Exception e) {
                Log.e("ERROR", "getUser API ERROR", e);
            }
            return new CourseContent.Course();
        }

        @Override
        protected void onPostExecute(CourseContent.Course lastCourse) {

        }
    }

    public static class AddUser extends AsyncTask<String, Void, CourseContent.Course> {
        @Override
        protected CourseContent.Course doInBackground(String... params) {
            try {
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6969/addUser?netId=");

                URL.append(params[0]);

                URL.append("&password=");

                URL.append(params[1]);

                URL url = new URL(URL.toString());
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNextLine()) {
                    Log.d("API", "addUser: " + sc.nextLine());
                }
            } catch (IOException e) {
                Log.e("ERROR", "getUser API ERROR", e);
            }
            return new CourseContent.Course();
        }

        @Override
        protected void onPostExecute(CourseContent.Course lastCourse) {

        }
    }

    public static class AddClasses extends AsyncTask<String, Void, CourseContent.Course> {
        @Override
        protected CourseContent.Course doInBackground(String... params) {
            try {
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6969/addClasses?netId=");

                URL.append(params[0]);

                URL.append("&password=");

                URL.append(params[1]);

                URL.append("&classes=");

                for (int i = 1; i < params.length; i++) {
                    URL.append(params[i]);
                    URL.append(",");
                }

                URL.deleteCharAt(URL.length() - 1);

                URL url = new URL(URL.toString());
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNextLine()) {
                    Log.d("API", "getUser: " + sc.nextLine());
                }
            } catch (IOException e) {
                Log.e("ERROR", "getUser API ERROR", e);
            }
            return new CourseContent.Course();
        }

        @Override
        protected void onPostExecute(CourseContent.Course lastCourse) {

        }
    }

    public static class DeleteClasses extends AsyncTask<String, Void, CourseContent.Course> {
        @Override
        protected CourseContent.Course doInBackground(String... params) {
            try {
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6969/deleteClass?netId=");

                URL.append(params[0]);

                URL.append("&password=");

                URL.append(params[1]);

                URL.append("&classes=");

                URL url = new URL(URL.toString());
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNextLine()) {
                    Log.d("API", "getUser: " + sc.nextLine());
                }
            } catch (IOException e) {
                Log.e("ERROR", "getUser API ERROR", e);
            }
            return new CourseContent.Course();
        }

        @Override
        protected void onPostExecute(CourseContent.Course lastCourse) {

        }
    }
}
