package com.schedufy.user.schedufy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

public class EventDatabaseAdapter extends SQLiteOpenHelper {
    public SQLiteDatabase db;

    public static final String DATABASE_NAME = "events_database";
    public static final String TABLE_NAME = "events_table";
    public static final int DATABASE_VERSION = 1;

    public static final String COL_UID = "_id";
    public static final String COL_CATEGORY = "Category";
    public static final String COL_DATE = "Date";
    public static final String COL_TIME = "Time";
    public static final String COL_DESCRIPTION = "Description";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COL_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CATEGORY + " VARCHAR(255), " + COL_DATE + " VARCHAR(255), " + COL_TIME + " VARCHAR(255), " + COL_DESCRIPTION + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public EventDatabaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    public long insertEvent(String category, String date, String time, String description) {
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CATEGORY, category);
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_TIME, time);
        contentValues.put(COL_DESCRIPTION, description);

        long id = db.insert(TABLE_NAME, null, contentValues);

        return id;
    }

    public String getAllEvents() {
        db = getWritableDatabase();
        String[] columns = {COL_UID, COL_CATEGORY, COL_DATE, COL_TIME, COL_DESCRIPTION};
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext())
        {
            int cid = cursor.getInt(0);
            String category = cursor.getString(1);
            String date = cursor.getString(2);
            String time = cursor.getString(3);
            String description = cursor.getString(4);

            buffer.append(cid + " " + category + " " + date + " " + time + " " + description + "\n");
        }

        return buffer.toString();
    }

    public Cursor getAllRows() {
        db = getWritableDatabase();
        String columns[] = {COL_UID, COL_CATEGORY, COL_DATE, COL_TIME, COL_DESCRIPTION};
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}

