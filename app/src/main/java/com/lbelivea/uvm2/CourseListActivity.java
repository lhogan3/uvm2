package com.lbelivea.uvm2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.app.NavUtils;
import androidx.appcompat.app.ActionBar;

import android.view.MenuItem;
import com.lhogan.uvm2.CourseContent.Course;
import com.lhogan.uvm2.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Courses. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CourseDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class CourseListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private static SimpleItemRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public static boolean isMyCourseList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        if(CourseListActivity.isMyCourseList){
            Log.d("INFO", "static initializer: ---------- my courses ");
            CourseContent.clearLists();
            for (Course c:LoggedInUser.getCourses()) {
                CourseContent.addItem(c);
            }

        } else {
            Log.d("INFO", "static initializer: ---------- course list");
            new CourseContent.Scraping().execute();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        mAdapter = new SimpleItemRecyclerViewAdapter(this, mTwoPane);

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

        mRecyclerView = findViewById(R.id.course_list);
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
        final List<Course> filteredCourseList = filter(query);
        mAdapter.clear();
        mAdapter.add(filteredCourseList);
        mAdapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query){
        return false;
    }

    private static List<Course> filter(String query){
        String[] queries = query.toLowerCase().split(" ");

        final List<Course> allCourses = CourseContent.COURSES;
        final List<Course> filteredCourseList = new ArrayList<>();

        boolean add;

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
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
        private final CourseListActivity mParentActivity;
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

        SimpleItemRecyclerViewAdapter(CourseListActivity parent,
                                      boolean twoPane) {
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        public void add(Course course){
            mValues.add(course);
            notifyDataSetChanged();
        }

        public void remove(Course course){
            mValues.remove(course);
            notifyDataSetChanged();
        }

        public void add(List<Course> courses){
            mValues.addAll(courses);
            notifyDataSetChanged();
        }

        public void remove(List<Course> courses){
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
