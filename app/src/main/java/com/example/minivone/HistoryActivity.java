package com.example.minivone;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView rvHistory;
    ArrayList<Patient> patientList;
    HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvHistory = findViewById(R.id.rvHistory);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));

        patientList = new ArrayList<>();

        // Database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM patients", null);

        if (c.getCount() == 0) {
            patientList.add(new Patient("No patient history available", "", "", R.drawable.profile));
        } else {
            while (c.moveToNext()) {
                String name = c.getString(1);
                String prediction = c.getString(6);
                String confidence = c.getString(7);

                patientList.add(new Patient(name, prediction, confidence, R.drawable.profile));
            }
        }

        adapter = new HistoryAdapter(this, patientList);
        rvHistory.setAdapter(adapter);

        c.close();
        db.close();
    }
}