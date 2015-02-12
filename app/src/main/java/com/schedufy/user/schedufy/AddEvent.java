package com.schedufy.user.schedufy;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
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


public class AddEvent extends Activity {

    Calendar mCurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
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

        mDatePickerDialog = new DatePickerDialog(AddEvent.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCurrentDate.set(Calendar.YEAR, year);
                mCurrentDate.set(Calendar.MONTH, monthOfYear);
                mCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateLabel();
            }
        }, mYear, mMonth, mDay);

        mDatePickerDialog.show();
    }

    private void updateDateLabel() {
        EditText date = (EditText) findViewById(R.id.editTextDate);
        String format = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.CANADA);
        date.setText(dateFormat.format(mCurrentDate.getTime()));
    }

    public void addingEvent(View v) {
        Intent returnIntent = new Intent();

        EditText category    = (EditText) findViewById(R.id.editTextCategory);
        EditText date        = (EditText) findViewById(R.id.editTextDate);
        TimePicker time      = (TimePicker) findViewById(R.id.timePicker);
        //EditText time        = (EditText) findViewById(R.id.editTextTime);
        EditText description = (EditText) findViewById(R.id.editTextDescription);

        String returnCategory    = category.getText().toString();
        String returnDate        = date.getText().toString();
        String returnTime        = time.getCurrentHour().toString()
                                   + ":" + time.getCurrentMinute().toString();
        String returnDescription = description.getText().toString();

        Toast.makeText(getApplication(), R.string.event_added, Toast.LENGTH_LONG).show();

        returnIntent.putExtra("category", returnCategory);
        returnIntent.putExtra("date", returnDate);
        returnIntent.putExtra("time", returnTime);
        returnIntent.putExtra("description", returnDescription);

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
