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

/**
 * A user can enter data into fields. This data will be inserted into the database.
 */
public class AddEventActivity extends Activity {

    // Constant strings
    private static final String EVENT_ADDED = "Event Added!";
    private static final String EVENT_NOT_ADDED = "Error!";

    // Instance members
    private Calendar mCurrentDate;
    private Calendar mCurrentTime;
    private EditText mEditCategory;
    private EditText mEditDate;
    private EditText mEditTime;
    private EditText mEditDescription;
    private EventDatabase mEventHelper;
    private String dateFromCalendar;

    /**
     * Sets the instance members for the four fields and the database.
     * @param savedInstanceState Application state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        mEditCategory = (EditText) findViewById(R.id.editTextCategory);
        mEditDate = (EditText) findViewById(R.id.editTextDate);
        mEditTime = (EditText) findViewById(R.id.editTextTime);
        mEditDescription = (EditText) findViewById(R.id.editTextDescription);

        Bundle extras = getIntent().getExtras();

        // Checks to see if a date was passed from the CalendarView, if so, put it in the date
        // field.
        if(extras != null)
        {
            dateFromCalendar = extras.getString("DateFromCalendar");
            mEditDate.setText(dateFromCalendar);
        }

        mEventHelper = new EventDatabase(this);
    }

    /**
     * Inflates the menu.
     * @param menu Options menu.
     * @return True when the menu is inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_event, menu);
        return true;
    }

    /**
     * Handles clicks on the menu.
     * @param item The item on the menu.
     * @return True when item clicked is a menu item.
     */
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

    /**
     * Bring up a date picker when user clicks the date field.
     * @param v The view
     */
    public void datePickerClick(View v) {
        DatePickerDialog mDatePickerDialog;

        mCurrentDate = Calendar.getInstance();
        int mYear = mCurrentDate.get(Calendar.YEAR);
        int mMonth = mCurrentDate.get(Calendar.MONTH);
        int mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        mDatePickerDialog = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
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

    /**
     * Brings up a time picker when user clicks the time field.
     * @param v
     */
    public void timePickerClick(View v) {
        TimePickerDialog mTimePickerDialog;

        mCurrentTime = Calendar.getInstance();
        int mHour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int mMinute = mCurrentTime.get(Calendar.MINUTE);

        mTimePickerDialog = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                mCurrentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mCurrentTime.set(Calendar.MINUTE, minute);
                updateTimeLabel();
            }
        }, mHour, mMinute, true);

        mTimePickerDialog.show();
    }

    /**
     * Sets the format for the date entry.
     */
    private void updateDateLabel() {
        EditText date = (EditText) findViewById(R.id.editTextDate);
        String format = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.CANADA);
        date.setText(dateFormat.format(mCurrentDate.getTime()));
    }

    /**
     * Sets the format for the time entry.
     */
    private void updateTimeLabel() {
        EditText time = (EditText) findViewById(R.id.editTextTime);
        String format = "HH:mm:ss";
        SimpleDateFormat timeFormat = new SimpleDateFormat(format, Locale.CANADA);
        time.setText(timeFormat.format(mCurrentTime.getTime()));
    }

    /**
     * Pulls strings out of EditText fields and puts them into one database record.
     * @param v
     */
    public void addEventToDatabase(View v) {
        String category = mEditCategory.getText().toString();
        String date = mEditDate.getText().toString();
        String time = mEditTime.getText().toString();
        String description = mEditDescription.getText().toString();

        if (mEventHelper.insertEvent(category, date, time, description) < 0) {
            Toast.makeText(getApplication(), EVENT_NOT_ADDED, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplication(), EVENT_ADDED, Toast.LENGTH_LONG).show();
            mEditCategory.setText("");
            mEditDate.setText("");
            mEditTime.setText("");
            mEditDescription.setText("");
        }
    }

    /**
     * Return to the previous activity.
     * @param v
     */
    public void leaveEvent(View v) {
        finish();
    }
}
