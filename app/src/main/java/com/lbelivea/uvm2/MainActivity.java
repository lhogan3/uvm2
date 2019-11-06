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

public class MainActivity extends AppCompatActivity {

    private static int MESSAGE_ID = 0;
    private Handler mResponseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // WE NEED HANDLER :(

        mResponseHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_ID) {

                }
            }
        };

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
                //login screen
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
