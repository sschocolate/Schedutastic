package com.schedufy.user.schedufy;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockFragmentActivity {
    private ViewPager mViewPager;
    private TabsAdapter mTabsAdapter;
    private EventDatabaseAdapter mEventHelper;
    private ListView list;
    private String category;
    private String date;
    private String time;
    private String description;
    static Context context;

    /**
     * Creates navigational structure of the application.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.pager);
        setContentView(mViewPager);

        final ActionBar bar = getSupportActionBar();
        bar.setTitle(R.string.app_name);
        bar.setBackgroundDrawable(getResources().getDrawable(R.color.schedufy_red));
        bar.setStackedBackgroundDrawable(getResources().getDrawable(R.color.schedufy_red));
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mTabsAdapter = new TabsAdapter(this, mViewPager);
        mTabsAdapter.addTab(bar.newTab().setText(R.string.home), HomeFragment.class, null);
        mTabsAdapter.addTab(bar.newTab().setText(R.string.calendar), CalendarFragment.class, null);
        //mTabsAdapter.addTab(bar.newTab().setText(R.string.events), EventsFragment.class, null);

        mEventHelper = new EventDatabaseAdapter(this);
    }

    /**
     * Creates a drop down settings menu.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Switches activity from the Main page to a Settings page using Intents.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, Schedufy_settings.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Pulls data out of an Intent using request and result codes.
     * This will be used to find added Event data.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data.getExtras().containsKey("category")) {
            category = data.getStringExtra("category");
        }

        if(data.getExtras().containsKey("date")) {
            date = data.getStringExtra(("date"));
        }

        if(data.getExtras().containsKey("time")) {
            time = data.getStringExtra("time");
        }

        if(data.getExtras().containsKey("description")) {
            description = data.getStringExtra("description");
        }
    }

    /**
     * Switches to the AddEvent activity using an Intent.
     * startActivityForResult gets data back to this activity.
     * @param view
     */
    public void toAddEvent(View view) {
        Intent i = new Intent(this, Event.class);
        startActivity(i);
    }
}
