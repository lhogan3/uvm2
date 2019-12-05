package com.lbelivea.uvm2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.lhogan.uvm2.CourseContent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.lhogan.uvm2.CourseContent.Course;


public class MyCourseListActivity extends AppCompatActivity {

    private boolean mTwoPane;
    private static MyCourseListActivity.SimpleItemRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public static MyCourseListActivity.SimpleItemRecyclerViewAdapter getmAdapter(){
        return mAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // log my courses
        Log.d("INFO", "static initializer: ---------- my courses ");

        // clear course content
        CourseContent.clearLists();

        // add courses that are in my course list
        for (Course c:LoggedInUser.getCourses()) {
            CourseContent.addItem(c);
        }

        // set the title to my courses
        toolbar.setTitle("My courses");

        mAdapter = new MyCourseListActivity.SimpleItemRecyclerViewAdapter(this, mTwoPane);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.course_detail_container) != null) {
            mTwoPane = true;
        }

        mRecyclerView = findViewById(R.id.course_list);
        assert mRecyclerView != null;
        setupRecyclerView(mRecyclerView);
        mAdapter.refresh();

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(mAdapter);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final MyCourseListActivity mParentActivity;
        private final List<CourseContent.Course> mValues = new ArrayList<>();
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course course = (Course) view.getTag();

                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(CourseDetailFragment.ARG_ITEM_ID, course.CRN);
                    CourseDetailFragment fragment = new CourseDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.course_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, CourseDetailActivity.class);
                    intent.putExtra(CourseDetailFragment.ARG_ITEM_ID, course.CRN);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(MyCourseListActivity parent,
                                      boolean twoPane) {
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        public void refresh(){
            mValues.clear();
            mValues.addAll(CourseContent.COURSES);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.course_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mSubjectView.setText(mValues.get(position).subject);
            String numSec = mValues.get(position).number + " " + mValues.get(position).section;
            holder.mNumberView.setText(numSec);
            holder.mNameView.setText(mValues.get(position).name);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

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
