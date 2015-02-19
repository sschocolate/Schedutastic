package com.schedufy.user.schedufy;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Event extends Activity {

    private static final String EVENT_ADDED = "Event Added!";
    private static final String EVENT_NOT_ADDED = "Error!";

    private Calendar mCurrentDate;
    private Calendar mCurrentTime;
    private EditText editCategory;
    private EditText editDate;
    private EditText editTime;
    private EditText editDescription;
    private EventDatabaseAdapter mEventHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        editCategory    = (EditText) findViewById(R.id.editTextCategory);
        editDate        = (EditText) findViewById(R.id.editTextDate);
        editTime        = (EditText) findViewById(R.id.editTextTime);
        editDescription = (EditText) findViewById(R.id.editTextDescription);

        mEventHelper = new EventDatabaseAdapter(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void datePickerClick(View v) {
        DatePickerDialog mDatePickerDialog;

        mCurrentDate = Calendar.getInstance();
        int mYear = mCurrentDate.get(Calendar.YEAR);
        int mMonth = mCurrentDate.get(Calendar.MONTH);
        int mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        mDatePickerDialog = new DatePickerDialog(Event.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                mCurrentDate.set(Calendar.YEAR, year);
                mCurrentDate.set(Calendar.MONTH, monthOfYear);
                mCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateLabel();
            }
        }, mYear, mMonth, mDay);

        mDatePickerDialog.show();
    }

    public void timePickerClick(View v) {
        TimePickerDialog mTimePickerDialog;

        mCurrentTime = Calendar.getInstance();
        int mHour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int mMinute = mCurrentTime.get(Calendar.MINUTE);

        mTimePickerDialog = new TimePickerDialog(Event.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                mCurrentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mCurrentTime.set(Calendar.MINUTE, minute);
                updateTimeLabel();
            }
        }, mHour, mMinute, true);

        mTimePickerDialog.show();
    }

    private void updateDateLabel() {
        EditText date = (EditText) findViewById(R.id.editTextDate);
        String format = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.CANADA);
        date.setText(dateFormat.format(mCurrentDate.getTime()));
    }

    private void updateTimeLabel() {
        EditText time = (EditText) findViewById(R.id.editTextTime);
        String format = "HH:mm";
        SimpleDateFormat timeFormat = new SimpleDateFormat(format, Locale.CANADA);
        time.setText(timeFormat.format(mCurrentTime.getTime()));
    }

    public void addEventToDatabase(View v) {
        String category = editCategory.getText().toString();
        String date = editDate.getText().toString();
        String time = editTime.getText().toString();
        String description = editDescription.getText().toString();

        if(mEventHelper.insertEvent(category, date, time, description) < 0) {
            Toast.makeText(getApplication(), EVENT_NOT_ADDED, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplication(), EVENT_ADDED, Toast.LENGTH_LONG).show();
        }
    }

    public void leaveEvent(View v) {
        finish();
    }
}
