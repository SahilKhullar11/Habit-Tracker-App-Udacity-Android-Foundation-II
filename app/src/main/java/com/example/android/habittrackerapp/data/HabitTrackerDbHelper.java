package com.example.android.habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.habittrackerapp.data.HabitTrackerContract.HabitTrackerEntry;

public class HabitTrackerDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "habit.db";
    public static final int DATABASE_VERSION = 1;

    public HabitTrackerDbHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITTRACKER_TABLE = "CREATE TABLE " + HabitTrackerEntry.TABLE_NAME + "("
                + HabitTrackerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitTrackerEntry.HABIT_NAME + " TEXT NOT NULL, "
                + HabitTrackerEntry.HABIT_TYPE + " TEXT NOT NULL, "
                + HabitTrackerEntry.HABIT_FREQUENCY + " INTEGER DEFAULT 0);";
        db.execSQL(SQL_CREATE_HABITTRACKER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
