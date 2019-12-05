package com.lbelivea.uvm2;

import android.os.AsyncTask;
import android.util.Log;

import com.lhogan.uvm2.CourseContent;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class ApiInteractions {

    // get user
    public static class GetUser extends AsyncTask<String, Void, CourseContent.Course> {
        private static boolean loginResult = true;
        @Override
        protected CourseContent.Course doInBackground(String... params) {
            try {
                // url for proxy server
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6789/getUser?netId=");

                // get the net id
                URL.append(params[0]);

                // add the place for password
                URL.append("&password=");

                // get the password
                URL.append(params[1]);

                // make it a url
                URL url = new URL(URL.toString());

                // open the page
                Scanner sc = new Scanner(url.openStream());

                // get the response
                String APIResponse = sc.nextLine();

                // log it
                Log.d("API", "getUser: " + APIResponse);

                // parse the courses
                LoggedInUser.parseCourses(APIResponse);

                // login is true
                loginResult = true;

            } catch (Exception e) {
                // error
                Log.d("info", "login Failed");
                loginResult = false;
            }

            // return
            return new CourseContent.Course();
        }

        @Override
        protected void onPostExecute(CourseContent.Course lastCourse) {
            // attempt login
            LoginActivity.loginActivityInstance.attemptLogin(loginResult);
        }
    }

    // add user
    public static class AddUser extends AsyncTask<String, Void, CourseContent.Course> {
        @Override
        protected CourseContent.Course doInBackground(String... params) {
            try {
                // url for proxy server
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6789/addUser?netId=");

                // append netId
                URL.append(params[0]);

                // append place for password
                URL.append("&password=");

                // append password
                URL.append(params[1]);

                // create url
                URL url = new URL(URL.toString());

                // open url
                Scanner sc = new Scanner(url.openStream());

                // readline until end
                while (sc.hasNextLine()) {
                    // log it
                    Log.d("API", "addUser: " + sc.nextLine());
                }
            } catch (IOException e) {
                // error
                Log.e("ERROR", "addUser API ERROR", e);
            }

            // return
            return new CourseContent.Course();
        }

        @Override
        protected void onPostExecute(CourseContent.Course lastCourse) {

        }
    }

    // add classes
    public static class AddClasses extends AsyncTask<String, Void, CourseContent.Course> {
        @Override
        protected CourseContent.Course doInBackground(String... params) {
            try {
                // proxy server url
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6789/addClasses?netId=");

                // append net id
                URL.append(params[0]);

                // append place for password
                URL.append("&password=");

                // append password
                URL.append(params[1]);

                // append place for classes
                URL.append("&classes=");

                // add all courses
                for (int i = 2; i < params.length; i++) {
                    // append
                    URL.append(params[i]);
                    URL.append(",");
                }

                // delete comma at the end
                URL.deleteCharAt(URL.length() - 1);

                // create url
                URL url = new URL(URL.toString());

                // open url
                Scanner sc = new Scanner(url.openStream());

                // read response
                while (sc.hasNextLine()) {
                    // log
                    Log.d("API", "addClasses: " + sc.nextLine());
                }
            } catch (IOException e) {
                // error
                Log.e("ERROR", "addClasses API ERROR", e);
            }

            // return
            return new CourseContent.Course();
        }

        @Override
        protected void onPostExecute(CourseContent.Course lastCourse) {

        }
    }

    // delete courses
    public static class DeleteClasses extends AsyncTask<String, Void, CourseContent.Course> {
        @Override
        protected CourseContent.Course doInBackground(String... params) {
            try {
                // proxy server url
                StringBuilder URL = new StringBuilder("http://73.219.102.187:6789/deleteClass?netId=");

                // append net id
                URL.append(params[0]);

                // append place for password
                URL.append("&password=");

                // append password
                URL.append(params[1]);

                // append place for classes
                URL.append("&classes=");

                // delete classes
                for (int i = 2; i < params.length; i++) {
                    // append classes to delete
                    URL.append(params[i]);
                    URL.append(",");
                }

                // delete comma at end
                URL.deleteCharAt(URL.length() - 1);

                // create url
                URL url = new URL(URL.toString());

                // open url
                Scanner sc = new Scanner(url.openStream());

                // read response
                while (sc.hasNextLine()) {
                    // log it
                    Log.d("API", "deleteClass: " + sc.nextLine());
                }
            } catch (IOException e) {
                // error
                Log.e("ERROR", "deleteClass API ERROR", e);
            }

            // return
            return new CourseContent.Course();
        }

        @Override
        protected void onPostExecute(CourseContent.Course lastCourse) {

        }
    }
}
