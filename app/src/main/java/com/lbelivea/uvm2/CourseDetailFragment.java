package com.lbelivea.uvm2;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lhogan.uvm2.CourseContent;

/**
 * A fragment representing a single Course detail screen.
 * This fragment is either contained in a {@link CourseListActivity}
 * in two-pane mode (on tablets) or a {@link CourseDetailActivity}
 * on handsets.
 */
public class CourseDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The course content this fragment is presenting.
     */
    private CourseContent.Course mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CourseDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the course content specified by the fragment
            mItem = CourseContent.COURSE_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.subject + " " + mItem.number + " " + mItem.section);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.course_detail, container, false);

        // Show the course content in a readable format in a TextView.
        if (mItem != null) {
            String instructor = "Instructor: " + mItem.instructor;
            String location = mItem.building + " " + mItem.room;
            String time = mItem.days + " " + mItem.startTime + "-" + mItem.endTime;
            String contactInfo = mItem.netID + " " + mItem.email;
            String credits = mItem.credits + " credit(s) (" + mItem.lecLab + ")";
            String seats = mItem.currentEnrollment + " out of " + mItem.maxEnrollment + " seats full";

            ((TextView) rootView.findViewById(R.id.course_name)).setText(mItem.name);
            ((TextView) rootView.findViewById(R.id.instructor)).setText(instructor);
            ((TextView) rootView.findViewById(R.id.location)).setText(location);
            ((TextView) rootView.findViewById(R.id.day_time)).setText(time);
            ((TextView) rootView.findViewById(R.id.email)).setText(contactInfo);
            ((TextView) rootView.findViewById(R.id.credits)).setText(credits);
            ((TextView) rootView.findViewById(R.id.seats)).setText(seats);
        }

        return rootView;
    }
}
