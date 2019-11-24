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

    public static class AddUser extends AsyncTask<String, Void, CourseContent.Course> {
        @Override
        protected CourseContent.Course doInBackground(String... params) {
            try {
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6969/getUser?netId=");

                URL.append(params[0]);

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
