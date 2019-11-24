package com.lbelivea.uvm2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lhogan.uvm2.CourseContent;

import java.util.ArrayList;
import java.util.List;

public class MyCourseListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private static MyCourseListActivity.SimpleItemRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_course_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(getTitle());

        mAdapter = new MyCourseListActivity.SimpleItemRecyclerViewAdapter(this, mTwoPane);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.course_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        mRecyclerView = findViewById(R.id.my_course_list);
        assert mRecyclerView != null;
        setupRecyclerView(mRecyclerView);
        mAdapter.refresh();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_list, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);

        final MenuItem refreshItem = menu.findItem(R.id.refresh);

        return true;

    }

    @Override
    public boolean onQueryTextChange(String query){
        if(query.equals("")){
            mAdapter.refresh();
            return true;
        }
        final List<CourseContent.Course> filteredCourseList = filter(query);
        mAdapter.clear();
        mAdapter.add(filteredCourseList);
        mAdapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query){
        return false;
    }

    private static List<CourseContent.Course> filter(String query){
        String[] queries = query.toLowerCase().split(" ");

        final List<CourseContent.Course> allCourses = CourseContent.COURSES;
        final List<CourseContent.Course> filteredCourseList = new ArrayList<>();

        boolean add;

        for(CourseContent.Course course : allCourses){
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        else if (id == R.id.refresh) {
            new CourseContent.Scraping().execute();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(mAdapter);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<MyCourseListActivity.SimpleItemRecyclerViewAdapter.ViewHolder> {
        private final MyCourseListActivity mParentActivity;
        private final List<CourseContent.Course> mValues = new ArrayList<>();
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CourseContent.Course course = (CourseContent.Course) view.getTag();
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

        public void add(CourseContent.Course course){
            mValues.add(course);
            notifyDataSetChanged();
        }

        public void remove(CourseContent.Course course){
            mValues.remove(course);
            notifyDataSetChanged();
        }

        public void add(List<CourseContent.Course> courses){
            mValues.addAll(courses);
            notifyDataSetChanged();
        }

        public void remove(List<CourseContent.Course> courses){
            mValues.removeAll(courses);
            notifyDataSetChanged();
        }

        public void clear(){
            mValues.clear();
            notifyDataSetChanged();
        }

        public void refresh(){
            mValues.clear();
            mValues.addAll(CourseContent.COURSES);
            notifyDataSetChanged();
        }

        @Override
        public MyCourseListActivity.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.course_list_content, parent, false);
            return new MyCourseListActivity.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyCourseListActivity.SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.mSubjectView.setText(mValues.get(position).subject);
            String numSec = mValues.get(position).number + " " + mValues.get(position).section;
            holder.mNumberView.setText(numSec);
            holder.mNameView.setText(mValues.get(position).name);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        public static void resetAdapter(){
            mAdapter.refresh();
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
