package com.schedufy.user.schedufy;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A ListView of database items.
 * A button to switch activities where items can be added to the database.
 */
public class HomeFragment extends ListFragment {
    // Constraints for the fling gesture
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private View view;
    private ListView list;

    private EventDatabaseAdapter mDbAdapter;
    private Cursor mDbCursor;
    private SimpleCursorAdapter mCursorAdapter;
    private GestureDetector mGestureDetector;
    View.OnTouchListener mGestureListener;

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

        mDbAdapter = new EventDatabaseAdapter(getActivity());
        mDbAdapter.getWritableDatabase();
        mDbCursor = mDbAdapter.getAllRows();

        list = (ListView) view.findViewById(android.R.id.list);

        mGestureDetector = new GestureDetector(getActivity(), new GestureDetection());
        mGestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return mGestureDetector.onTouchEvent(event);
            }
        };

        list.setOnTouchListener(mGestureListener);
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
     * Used for the detection of swiping on ListView items.
     */
    class GestureDetection extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                    return false;
                }
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Toast.makeText(getActivity(), "LEFT", Toast.LENGTH_LONG).show();
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Toast.makeText(getActivity(), "RIGHT", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }

    /**
     * Sets the ListView adapter to the SimpleCursorAdapter which maps the database column into a ListView item layout.
     * @param v
     */
    public void setList(View v) {
        mCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.list_item,
                mDbCursor,
                new String[]{EventDatabaseAdapter.COL_UID, EventDatabaseAdapter.COL_CATEGORY, EventDatabaseAdapter.COL_DATE, EventDatabaseAdapter.COL_TIME, EventDatabaseAdapter.COL_DESCRIPTION},
                new int[]{R.id.list_uid, R.id.list_category, R.id.list_date, R.id.list_time, R.id.list_description},
                0
        );

        list.setAdapter(mCursorAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        String s = String.valueOf(id);
        Toast toast = Toast.makeText(getActivity(), s, Toast.LENGTH_LONG);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(30);
        toast.show();
    }
}
