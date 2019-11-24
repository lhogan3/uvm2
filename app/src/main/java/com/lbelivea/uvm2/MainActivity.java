package com.lbelivea.uvm2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static int MESSAGE_ID = 0;
    private Handler mResponseHandler;

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

        final Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent changeToLogin = new Intent(v.getContext(), LoginActivity.class);
                startActivity(changeToLogin);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.main:
                //main screen
                break;
            case R.id.login:
                //authentication screen
                Intent changeToLogin = new Intent(this, LoginActivity.class);
                startActivity(changeToLogin);
                break;
            case R.id.list:
                //course listing
                Intent changeToList = new Intent(this, CourseListActivity.class);
                startActivity(changeToList);
                break;
            case R.id.mylist:
                //my saved courses
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
