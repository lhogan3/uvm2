package com.lbelivea.uvm2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.NavUtils;

import android.view.MenuItem;

import com.lbelivea.uvm2.ApiInteractions.*;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        if(CourseListActivity.isMyCourseList){
            fab.setImageResource(R.drawable.ic_delete_black_24dp);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!CourseListActivity.isMyCourseList) {
                    Snackbar.make(view, "Added to your list!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    new AddClasses().execute(LoginActivity.user.getNetId(),
                            LoginActivity.user.getPassword(), CourseDetailFragment.mItem.getCRN());
                    LoginActivity.user.addCourse(CourseDetailFragment.mItem);
                }
                else{
                    Snackbar.make(view, "Deleted from your list!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    new DeleteClasses().execute(LoginActivity.user.getNetId(),
                            LoginActivity.user.getPassword(), CourseDetailFragment.mItem.getCRN());
                    LoginActivity.user.deleteCourse(CourseDetailFragment.mItem.getCRN());
                    Intent changeToList = new Intent(view.getContext(), MyCourseListActivity.class);
                    startActivity(changeToList);
                }
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(CourseDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(CourseDetailFragment.ARG_ITEM_ID));
            CourseDetailFragment fragment = new CourseDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.course_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, CourseListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
