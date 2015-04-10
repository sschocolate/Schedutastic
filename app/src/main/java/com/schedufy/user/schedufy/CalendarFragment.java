package com.schedufy.user.schedufy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarFragment extends SherlockFragment {

    private View view;
    private Calendar calendar;
    private CalendarView calendarView;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = (CalendarView) view.findViewById(R.id.calendarView);

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
