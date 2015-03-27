package com.schedufy.user.schedufy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database adapter class: manages database.
 */
public class EventDatabase extends SQLiteOpenHelper {
    public SQLiteDatabase db;

    // Database details
    public static final String DATABASE_NAME = "events_database";
    public static final String TABLE_NAME = "events_table";
    public static final int DATABASE_VERSION = 1;

    // Database columns
    public static final String COL_UID = "_id";
    public static final String COL_CATEGORY = "Category";
    public static final String COL_DATE = "Date";
    public static final String COL_TIME = "Time";
    public static final String COL_DESCRIPTION = "Description";

    // SQL commands
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COL_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CATEGORY + " VARCHAR(255), " + COL_DATE + " VARCHAR(255), " + COL_TIME + " VARCHAR(255), " + COL_DESCRIPTION + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    /**
     * Constructor: calls base class SQLiteOpenHelper constructor
     * @param context
     */
    public EventDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the table using SQL command
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
    }

    /**
     * When the database changes upgrade will change the version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    /**
     * Inserts an record (an event) into the database.
     * @param category
     * @param date
     * @param time
     * @param description
     * @return
     */
    public long insertEvent(String category, String date, String time, String description) {
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CATEGORY, category);
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_TIME, time);
        contentValues.put(COL_DESCRIPTION, description);

        return db.insert(TABLE_NAME, null, contentValues);
    }

    /**
     * Removes a record (an event) from the database.
     * @param id
     */
    public void removeEvent(int id) {
        db = getWritableDatabase();

    }

    /**
     * Retrieves all the records in the database.
     * @return Records stored in a cursor
     */
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

