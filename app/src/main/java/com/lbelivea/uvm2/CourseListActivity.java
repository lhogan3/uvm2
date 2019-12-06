package com.lbelivea.uvm2;

//Importing necessary libraries
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.lhogan.uvm2.CourseContent;
import com.lhogan.uvm2.CourseContent.Course;

import java.util.ArrayList;
import java.util.List;

//Defining the CourseListActivity
public class CourseListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    //Defining the fields of the class
    private boolean mTwoPane;
    private static SimpleItemRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    public static boolean isMyCourseList = false;

    //Getting the mAdapter
    public static SimpleItemRecyclerViewAdapter getmAdapter(){
        return mAdapter;
    }

    //When the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the view.
        setContentView(R.layout.activity_course_list);

        //Creating the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        //Set the mAdapter
        mAdapter = new SimpleItemRecyclerViewAdapter(this, mTwoPane);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //If the app is being run on a tablet
        if (findViewById(R.id.course_detail_container) != null) {
            mTwoPane = true;
        }

        //Setting up the Recycler View
        mRecyclerView = findViewById(R.id.course_list);
        assert mRecyclerView != null;
        setupRecyclerView(mRecyclerView);
        mAdapter.refresh();

    }

    //Creating view
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_list, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;

    }

    //Filter for searching for a course.
    @Override
    public boolean onQueryTextChange(String query){
        if(query.equals("")){
            //on empty query, get the full list
            mAdapter.refresh();
            return true;
        }
        //filter to the query and get the new filtered list
        final List<Course> filteredCourseList = filter(query);
        mAdapter.clear();
        mAdapter.add(filteredCourseList);
        mAdapter.notifyDataSetChanged();
        return true;
    }

    //Have to override the the method so can extend the class.
    @Override
    public boolean onQueryTextSubmit(String query){
        //submitting the search query does nothing;
        //it performs the search whenever the text is changed
        return false;
    }

    //Filtering courses.
    private static List<Course> filter(String query){
        //split the query into multiple keywords to search separately
        String[] queries = query.toLowerCase().split(" ");

        final List<Course> allCourses = CourseContent.COURSES;
        final List<Course> filteredCourseList = new ArrayList<>();

        boolean add;

        //add all courses that match EVERY keyword
        for(Course course : allCourses){
            add = true;
            final String text = course.getData().toLowerCase();
            for(String q : queries){
                if(!text.contains(q)){
                    add = false;
                }
            }
            if(add){
                filteredCourseList.add(course);
            }
        }
        return filteredCourseList;
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Setting up the Recycler View
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //Setting the adapter
        recyclerView.setAdapter(mAdapter);
    }

    //Defining the SimpleItemRecyclerViewAdapter class
    public static class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        //Defining the fields for this class.
        private final CourseListActivity mParentActivity;
        private final List<CourseContent.Course> mValues = new ArrayList<>();
        private final boolean mTwoPane;

        //If an item is clicked in the list.
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Create a course with the views.getTag()
                Course course = (Course) view.getTag();

                //If the app is being run on a tablet.
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(CourseDetailFragment.ARG_ITEM_ID, course.CRN);
                    CourseDetailFragment fragment = new CourseDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.course_detail_container, fragment)
                            .commit();
                }
                //if it is being run on a phone.
                else {
                    //Create a new intent, pass in the CRN of the class that was clicked on,
                    //and then switch to the CourseDetailActivity.
                    Context context = view.getContext();
                    Intent intent = new Intent(context, CourseDetailActivity.class);
                    intent.putExtra(CourseDetailFragment.ARG_ITEM_ID, course.CRN);
                    context.startActivity(intent);
                }
            }
        };

        //Constructor
        SimpleItemRecyclerViewAdapter(CourseListActivity parent,
                                      boolean twoPane) {
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        //Adding
        public void add(List<Course> courses){
            mValues.addAll(courses);
            notifyDataSetChanged();
        }

        //Clearing
        public void clear(){
            mValues.clear();
            notifyDataSetChanged();
        }

        //Refreshing
        public void refresh(){
            mValues.clear();
            mValues.addAll(CourseContent.COURSES);
            notifyDataSetChanged();
        }

        //Creating a ViewHolder
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.course_list_content, parent, false);
            return new ViewHolder(view);
        }

        //Setting values for a ViewHolder
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mSubjectView.setText(mValues.get(position).subject);
            String numSec = mValues.get(position).number + " " + mValues.get(position).section;
            holder.mNumberView.setText(numSec);
            holder.mNameView.setText(mValues.get(position).name);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        //Resetting the adapter.
        public static void resetAdapter(){
            if (mAdapter != null) {
                mAdapter.refresh();
            }
        }

        //Returns the number of items in the list.
        @Override
        public int getItemCount() {
            return mValues.size();
        }

        //Defining the ViewHolder class
        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mSubjectView;
            final TextView mNumberView;
            final TextView mNameView;

            //Constructor
            ViewHolder(View view) {
                super(view);
                mSubjectView = view.findViewById(R.id.subject);
                mNumberView = view.findViewById(R.id.number);
                mNameView = view.findViewById(R.id.name);
            }
        }
    }
}
