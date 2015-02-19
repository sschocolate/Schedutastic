package com.schedufy.user.schedufy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDatabaseAdapter {

    EventHelper helper;

    public EventDatabaseAdapter(Context context) {
        helper = new EventHelper(context);
    }

    public long insertEvent(String category, String date, String time, String description) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(EventHelper.CATEGORY, category);
        contentValues.put(EventHelper.DATE, date);
        contentValues.put(EventHelper.TIME, time);
        contentValues.put(EventHelper.DESCRIPTION, description);

        long id = db.insert(EventHelper.TABLE_NAME, null, contentValues);

        return id;
    }

    static class EventHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "eventsdatabase";
        private static final String TABLE_NAME = "EVENTSTABLE";
        private static final int DATABASE_VERSION = 1;

        private static final String UID = "_id";
        private static final String CATEGORY = "Category";
        private static final String DATE = "Date";
        private static final String TIME = "Time";
        private static final String DESCRIPTION = "Description";

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY + " VARCHAR(255), " + DATE + " VARCHAR(255), " + TIME + " VARCHAR(255), " + DESCRIPTION + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        private Context context;

        public EventHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }
}
