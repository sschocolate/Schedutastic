package com.schedufy.user.schedufy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * Calendar fragment displays a calendar that can be clicked. Upon click, it will transfer the
 * clicked date to the AddEventActivity page.
 */
public class CalendarFragment extends SherlockFragment {

    /**
     * View created
     * @param inflater inflates the view
     * @param container a container for the view
     * @param savedInstanceState saved instance from previous open state
     * @return the instance of the view
     */
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            /**
             * When a date in the calendar is clicked, creates a string with the date and passes
             * it into the addEvent activity.
             * @param view the view to look in
             * @param year the selected year
             * @param month the selected month
             * @param dayOfMonth the selected day of the month
             */
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String newDate = year + "-" + month + "-" + dayOfMonth;
                Log.d("NEW_DATE", newDate);
                Intent addEvent = new Intent(getActivity(), AddEventActivity.class);
                addEvent.putExtra("DateFromCalendar", newDate);
                startActivity(addEvent);
            }
        });


        return view;
    }
}
