package com.schedufy.user.schedufy;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class HomeFragment extends ListFragment {

    private EventDatabaseAdapter dbAdapter;
    private ListView list;
    private SimpleCursorAdapter cursorAdapter;
    private Cursor dbCursor;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        dbAdapter = new EventDatabaseAdapter(getActivity());
        dbAdapter.getWritableDatabase();
        dbCursor = dbAdapter.getAllRows();

        list = (ListView) view.findViewById(android.R.id.list);

        setList(view);

        return view;
    }

    public void setList(View v)
    {
        cursorAdapter = new SimpleCursorAdapter(
            getActivity(),
            R.layout.list_item,
            dbCursor,
            new String[]{dbAdapter.COL_UID, dbAdapter.COL_CATEGORY, dbAdapter.COL_DATE, dbAdapter.COL_TIME, dbAdapter.COL_DESCRIPTION},
            new int[]{R.id.list_uid, R.id.list_category, R.id.list_date, R.id.list_time, R.id.list_description},
            0
        );

        list.setAdapter(cursorAdapter);
    }
}
