package com.schedufy.user.schedufy;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * A ListView of database items.
 * A button to switch activities where items can be added to the database.
 */
public class HomeFragment extends ListFragment {
    private View view;
    private ListView list;

    private EventDatabase mDbAdapter;
    private Cursor mDbCursor;

    /**
     * Shows ListView of database items.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        mDbAdapter = new EventDatabase(getActivity());
        mDbAdapter.getWritableDatabase();
        mDbCursor = mDbAdapter.getAllRows();

        list = (ListView) view.findViewById(android.R.id.list);

        setList(view);
        return view;
    }

    /**
     * Dynamically update ListView of database items.
     */
    @Override
    public void onResume() {
        super.onResume();

        mDbCursor = mDbAdapter.getAllRows();
        setList(view);
    }

    /**
     * Sets the ListView adapter to the SimpleCursorAdapter which maps the database column into a ListView item layout.
     * @param v
     */
    public void setList(View v) {
        SimpleCursorAdapter mCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.list_item,
                mDbCursor,
                new String[]{EventDatabase.COL_UID, EventDatabase.COL_CATEGORY, EventDatabase.COL_DATE, EventDatabase.COL_TIME, EventDatabase.COL_DESCRIPTION},
                new int[]{R.id.list_uid, R.id.list_category, R.id.list_date, R.id.list_time, R.id.list_description},
                0
        );

        list.setAdapter(mCursorAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){

    }
}
