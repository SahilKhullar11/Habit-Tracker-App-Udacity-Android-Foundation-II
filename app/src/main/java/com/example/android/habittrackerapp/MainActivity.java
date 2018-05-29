package com.example.android.habittrackerapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.habittrackerapp.data.HabitTrackerContract.HabitTrackerEntry;
import com.example.android.habittrackerapp.data.HabitTrackerDbHelper;

public class MainActivity extends AppCompatActivity {

    private TextView displayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        displayTextView = findViewById(R.id.display_text_view);
        displayTextView.setText(HabitTrackerEntry._ID + "-" + HabitTrackerEntry.HABIT_NAME + "-" +
                HabitTrackerEntry.HABIT_TYPE + "-" + HabitTrackerEntry.HABIT_FREQUENCY);

        Cursor cursor = readHabits();
        try {
            while (cursor.moveToNext()) {
                displayTextView.append("\n" + cursor.getString(cursor.getColumnIndex(HabitTrackerEntry._ID)) + "-" +
                        cursor.getString(cursor.getColumnIndex(HabitTrackerEntry.HABIT_NAME)) + "-" +
                        cursor.getString(cursor.getColumnIndex(HabitTrackerEntry.HABIT_TYPE)) + "-" +
                        cursor.getString(cursor.getColumnIndex(HabitTrackerEntry.HABIT_FREQUENCY)));
            }
        } finally {
            cursor.close();
        }
    }
    private Cursor readHabits()
    {
        HabitTrackerDbHelper mDbHelper = new HabitTrackerDbHelper(this, null);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String projection[] = {HabitTrackerEntry._ID, HabitTrackerEntry.HABIT_NAME,
                HabitTrackerEntry.HABIT_TYPE, HabitTrackerEntry.HABIT_FREQUENCY};
        return db.query(HabitTrackerEntry.TABLE_NAME, projection, null, null, null, null, null);
    }
}
