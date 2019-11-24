package com.lbelivea.uvm2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button buttonCourseList = findViewById(R.id.button_course_list);
        buttonCourseList.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent changeToList = new Intent(v.getContext(), CourseListActivity.class);
                startActivity(changeToList);
            }
        });

        final Button buttonMyList = findViewById(R.id.button_my_list);
        buttonMyList.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //change to my list
            }
        });
    }
}
