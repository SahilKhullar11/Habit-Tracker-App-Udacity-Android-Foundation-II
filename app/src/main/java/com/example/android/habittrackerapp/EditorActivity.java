package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.habittrackerapp.data.HabitTrackerContract.HabitTrackerEntry;
import com.example.android.habittrackerapp.data.HabitTrackerDbHelper;

public class EditorActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView typeTextView;
    private TextView frequencyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        nameTextView = findViewById(R.id.name_text_view);
        typeTextView = findViewById(R.id.type_text_view);
        frequencyTextView = findViewById(R.id.frequency_text_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_menu_item:
                insertHabit();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertHabit() {
        String name = nameTextView.getText().toString().trim();
        String type = typeTextView.getText().toString().trim();
        String freq = frequencyTextView.getText().toString().trim();
        int frequency = Integer.parseInt(freq);
        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitTrackerEntry.HABIT_NAME, name);
        contentValues.put(HabitTrackerEntry.HABIT_TYPE, type);
        contentValues.put(HabitTrackerEntry.HABIT_FREQUENCY, frequency);
        HabitTrackerDbHelper mDbHelper = new HabitTrackerDbHelper(this, null);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long id = db.insert(HabitTrackerEntry.TABLE_NAME, null, contentValues);
        if (id == -1)
            Toast.makeText(this, "Error saving Habit", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Habit saved", Toast.LENGTH_SHORT).show();
    }
}
