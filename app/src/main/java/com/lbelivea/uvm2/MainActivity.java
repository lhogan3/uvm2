package com.lbelivea.uvm2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.lhogan.uvm2.CourseContent;

public class MainActivity extends AppCompatActivity {
    public static boolean firstTimeThrough = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // scrape the courses from the uvm site
        new CourseContent.Scraping().execute();

        // button to go to course list
        final Button buttonCourseList = findViewById(R.id.button_course_list);
        buttonCourseList.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // set my course list to false
                CourseListActivity.isMyCourseList = false;

                // go to course list
                Intent changeToList = new Intent(v.getContext(), CourseListActivity.class);
                startActivity(changeToList);
            }
        });

        // button to go to my course list
        final Button buttonMyList = findViewById(R.id.button_my_list);
        buttonMyList.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // set my course list to true
                CourseListActivity.isMyCourseList = true;

                // go to my course list
                Intent changeToList = new Intent(v.getContext(), MyCourseListActivity.class);
                startActivity(changeToList);
            }
        });
    }
}
