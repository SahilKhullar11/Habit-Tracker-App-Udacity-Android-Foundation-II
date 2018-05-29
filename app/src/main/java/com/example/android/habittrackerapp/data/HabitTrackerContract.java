package com.example.android.habittrackerapp.data;

import android.provider.BaseColumns;

public class HabitTrackerContract {
    private HabitTrackerContract() {

    }

    public static class HabitTrackerEntry implements BaseColumns {
        public static final String TABLE_NAME = "habittracker";
        public static final String _ID = BaseColumns._ID;
        public static final String HABIT_NAME = "habitname";
        public static final String HABIT_TYPE = "habittype";
        public static final String HABIT_FREQUENCY = "frequency";
    }

}
