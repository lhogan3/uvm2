package com.lbelivea.uvm2;

//Import Necessary Libraries
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.lhogan.uvm2.CourseContent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.lhogan.uvm2.CourseContent.Course;

//Defining the MyCourseListActivity Class
public class MyCourseListActivity extends AppCompatActivity {

    //Declaring fields of this class.
    private boolean mTwoPane;
    private static MyCourseListActivity.SimpleItemRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;

    //Getter for the mAdapter.
    public static MyCourseListActivity.SimpleItemRecyclerViewAdapter getmAdapter(){
        return mAdapter;
    }

    //When the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the view.
        setContentView(R.layout.activity_course_list);

        //Create the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        //Clear Courses
        CourseContent.clearLists();

        //Add courses that are in MY_COURSE_LIST
        for (Course c:LoggedInUser.getCourses()) {
            CourseContent.addItem(c);
        }

        //Set Title
        toolbar.setTitle("My courses");

        //Set the madapter.
        mAdapter = new MyCourseListActivity.SimpleItemRecyclerViewAdapter(this, mTwoPane);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //If the app is being run on a tablet.
        if (findViewById(R.id.course_detail_container) != null) {
            mTwoPane = true;
        }

        //Setting up the Recycler View
        mRecyclerView = findViewById(R.id.course_list);
        assert mRecyclerView != null;
        setupRecyclerView(mRecyclerView);
        mAdapter.refresh();

    }

    //Setting up the Recycler View
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //Setting the adapter for the Recycler View
        recyclerView.setAdapter(mAdapter);
    }

    //Defining the SimpleItemRecyclerViewAdapter class.
    public static class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        //Defining the fields for this class.
        private final MyCourseListActivity mParentActivity;
        private final List<CourseContent.Course> mValues = new ArrayList<>();
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {

            //If an item in the list is clicked on.
            @Override
            public void onClick(View view) {

                //Set the course to be the one that was clicked on
                //by getting the tag.
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
                //If the app is being run on a phone.
                else {

                    //Pass the CRN of the course that is being clicked on into the intent,
                    //and then have the intent switch to the CourseDetailActivity.
                    Context context = view.getContext();
                    Intent intent = new Intent(context, CourseDetailActivity.class);
                    intent.putExtra(CourseDetailFragment.ARG_ITEM_ID, course.CRN);
                    context.startActivity(intent);
                }
            }
        };

        //Constructor
        SimpleItemRecyclerViewAdapter(MyCourseListActivity parent, boolean twoPane) {
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        //Refreshing the things being displayed.
        public void refresh(){
            mValues.clear();
            mValues.addAll(CourseContent.COURSES);
            notifyDataSetChanged();
        }

        //Create the viewHolder
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.course_list_content, parent, false);
            return new ViewHolder(view);
        }

        //Setting the values of the View Holder.
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mSubjectView.setText(mValues.get(position).subject);
            String numSec = mValues.get(position).number + " " + mValues.get(position).section;
            holder.mNumberView.setText(numSec);
            holder.mNameView.setText(mValues.get(position).name);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        //Return the number of items in the list.
        @Override
        public int getItemCount() {
            return mValues.size();
        }

        //Defining the ViewHolder class.
        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mSubjectView;
            final TextView mNumberView;
            final TextView mNameView;

            ViewHolder(View view) {
                super(view);
                mSubjectView = view.findViewById(R.id.subject);
                mNumberView = view.findViewById(R.id.number);
                mNameView = view.findViewById(R.id.name);
            }
        }
    }
}
