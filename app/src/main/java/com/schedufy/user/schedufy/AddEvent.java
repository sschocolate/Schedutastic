package com.schedufy.user.schedufy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddEvent extends Activity {

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

    public void addingEvent(View v) {
        Intent returnIntent = new Intent();

        EditText category    = (EditText) findViewById(R.id.editTextCategory);
        //EditText date        = (EditText) findViewById(R.id.editTextDate);
        //EditText time        = (EditText) findViewById(R.id.editTextTime);
        EditText description = (EditText) findViewById(R.id.editTextDescription);

        String returnCategory    = category.getText().toString();
        String returnDescription = description.getText().toString();

        Toast.makeText(getApplication(), R.string.event_added, Toast.LENGTH_LONG).show();

        returnIntent.putExtra("category", returnCategory);
        returnIntent.putExtra("description", returnDescription);

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
