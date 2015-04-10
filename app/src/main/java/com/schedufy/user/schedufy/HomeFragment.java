package com.schedufy.user.schedufy;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

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
        registerForContextMenu(list);

        return view;
    }

    /**
     * Creates a context menu.
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("What would you like to do?");
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Delete");
    }

    /**
     * A context menu to handle long item clicks
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getTitle().equals("Edit")) {
            Toast.makeText(getActivity(), "EDIT EVENT DATA", Toast.LENGTH_SHORT).show();
        } else if(item.getTitle().equals("Delete")) {
            mDbAdapter.removeEvent(info.id);
            onResume();
            Toast.makeText(getActivity(), "Deleted!", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
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
                new int[]{R.id.list_row, R.id.list_category, R.id.list_date, R.id.list_time},//, R.id.list_description},
                0
        );

        list.setAdapter(mCursorAdapter);
    }

    /**
     * Displays a description of the item (toast).
     * @param l the list to search
     * @param v the view
     * @param position the position in the ListView
     * @param id the ID of the ListView item
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Cursor c = mDbAdapter.getRowWithId(id);
        String desc = c.getString(c.getColumnIndex(EventDatabase.COL_DESCRIPTION));
        Toast.makeText(getActivity(), desc, Toast.LENGTH_LONG).show();
    }
}
