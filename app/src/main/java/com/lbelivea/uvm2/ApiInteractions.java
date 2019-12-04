package com.lbelivea.uvm2;

import android.os.AsyncTask;
import android.util.Log;

import com.lhogan.uvm2.CourseContent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class ApiInteractions {

    public static class GetUser extends AsyncTask<String, Void, CourseContent.Course> {
        private static boolean loginResult = true;
        @Override
        protected CourseContent.Course doInBackground(String... params) {
            try {
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6789/getUser?netId=");

                URL.append(params[0]);

                URL.append("&password=");

                URL.append(params[1]);

                URL url = new URL(URL.toString());
                Scanner sc = new Scanner(url.openStream());
                String APIResponse = sc.nextLine();
                Log.d("API", "getUser: " + APIResponse);

                LoggedInUser.parseCourses(APIResponse);

                loginResult = true;

            } catch (Exception e) {
                Log.d("info", "login Failed");
                loginResult = false;
            }
            return new CourseContent.Course();
        }

        @Override
        protected void onPostExecute(CourseContent.Course lastCourse) {
            LoginActivity.loginActivityInstance.attemptLogin(loginResult);
        }
    }

    public static class AddUser extends AsyncTask<String, Void, CourseContent.Course> {
        @Override
        protected CourseContent.Course doInBackground(String... params) {
            try {
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6789/addUser?netId=");

                URL.append(params[0]);

                URL.append("&password=");

                URL.append(params[1]);

                URL url = new URL(URL.toString());
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNextLine()) {
                    Log.d("API", "addUser: " + sc.nextLine());
                }
            } catch (IOException e) {
                Log.e("ERROR", "addUser API ERROR", e);
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
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6789/addClasses?netId=");

                URL.append(params[0]);

                URL.append("&password=");

                URL.append(params[1]);

                URL.append("&classes=");

                for (int i = 2; i < params.length; i++) {
                    URL.append(params[i]);
                    URL.append(",");
                }

                URL.deleteCharAt(URL.length() - 1);

                URL url = new URL(URL.toString());
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNextLine()) {
                    Log.d("API", "addClasses: " + sc.nextLine());
                }
            } catch (IOException e) {
                Log.e("ERROR", "addClasses API ERROR", e);
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
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6789/deleteClass?netId=");

                URL.append(params[0]);

                URL.append("&password=");

                URL.append(params[1]);

                URL.append("&classes=");

                for (int i = 2; i < params.length; i++) {
                    URL.append(params[i]);
                    URL.append(",");
                }

                URL.deleteCharAt(URL.length() - 1);

                URL url = new URL(URL.toString());
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNextLine()) {
                    Log.d("API", "deleteClass: " + sc.nextLine());
                }
            } catch (IOException e) {
                Log.e("ERROR", "deleteClass API ERROR", e);
            }
            return new CourseContent.Course();
        }

        @Override
        protected void onPostExecute(CourseContent.Course lastCourse) {

        }
    }
}
