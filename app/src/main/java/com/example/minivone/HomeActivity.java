package com.example.minivone;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.cardview.widget.CardView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    // Declare CardViews
    CardView cardCheckAlzheimer, cardViewHistory, cardProfile, cardSignOut;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Initialize CardViews
        cardCheckAlzheimer = findViewById(R.id.cardCheckAlzheimer);
        cardViewHistory = findViewById(R.id.cardViewHistory);
        cardProfile = findViewById(R.id.cardProfile);
        cardSignOut = findViewById(R.id.cardSignOut);

        // "Check Alzheimer" click
        cardCheckAlzheimer.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        });

        // "View History" click
        cardViewHistory.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
        });

        // "Profile" click
        cardProfile.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
        });

        // "Sign Out" click
        cardSignOut.setOnClickListener(v -> {

            mAuth.signOut();

            // 🔥 Clear saved data
            PrefManage prefManager = new PrefManage(this);
            prefManager.clearAll();

            Toast.makeText(HomeActivity.this, "Signed out successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(HomeActivity.this, LandingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}