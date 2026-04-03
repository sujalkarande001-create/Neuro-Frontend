package com.example.minivone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ResultActivity extends AppCompatActivity {

    TextView tvPatientName, tvPrediction, tvConfidence;
    Button btnAnother, btnViewHistory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvPatientName = findViewById(R.id.tvPatientName);
        tvPrediction = findViewById(R.id.tvPrediction);
        tvConfidence = findViewById(R.id.tvConfidence);

        btnAnother = findViewById(R.id.btnAnother);
        btnViewHistory = findViewById(R.id.btnViewHistory);

        btnAnother.setBackgroundResource(R.drawable.btn_rounded);
        btnViewHistory.setBackgroundResource(R.drawable.btn_rounded);
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String prediction = intent.getStringExtra("prediction");
        String confidence = intent.getStringExtra("confidence");

        tvPatientName.setText("Patient: " + name);
        tvPrediction.setText("Prediction: " + prediction);
        tvConfidence.setText("Confidence: " + confidence + "%");

        btnAnother.setOnClickListener(v -> {
            Intent i = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        });

        btnViewHistory.setOnClickListener(v -> {
            Intent i = new Intent(ResultActivity.this, HistoryActivity.class);
            startActivity(i);
        });
    }
}