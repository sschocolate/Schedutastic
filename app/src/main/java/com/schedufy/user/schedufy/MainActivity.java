package com.schedufy.user.schedufy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockFragmentActivity {
    private ViewPager mViewPager;
    private TabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.pager);
        setContentView(mViewPager);

        final ActionBar bar = getSupportActionBar();
        bar.setTitle("Schedufy");
        bar.setBackgroundDrawable(getResources().getDrawable(R.color.schedufy_red));
        bar.setStackedBackgroundDrawable(getResources().getDrawable(R.color.schedufy_red));
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mTabsAdapter = new TabsAdapter(this, mViewPager);
        mTabsAdapter.addTab(bar.newTab().setText("Home"), HomeFragment.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("Calendar"), CalendarFragment.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("Events"), EventsFragment.class, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
}
