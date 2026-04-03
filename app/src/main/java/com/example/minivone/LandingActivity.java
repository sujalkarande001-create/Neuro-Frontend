package com.example.minivone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LandingActivity extends AppCompatActivity {

    Button btnLogin, btnSignup;
    PrefManage prefManager;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManage(this);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // 🚀 Not first launch
        if (!prefManager.isFirstLaunch()) {

            if (currentUser != null) {

                // 🔥 Check profile completion
                if (!prefManager.isProfileCompleted()) {
                    startActivity(new Intent(LandingActivity.this, ProfileDataActivity.class));
                } else {
                    startActivity(new Intent(LandingActivity.this, HomeActivity.class));
                }

                finish();
                return;
            } else {
                startActivity(new Intent(LandingActivity.this, LoginActivity.class));
                finish();
                return;
            }
        }

        // First launch
        prefManager.setFirstLaunch(false);
        setContentView(R.layout.activity_landing);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(LandingActivity.this, LoginActivity.class));
            finish();
        });

        btnSignup.setOnClickListener(v -> {
            startActivity(new Intent(LandingActivity.this, SignupActivity.class));
            finish();
        });
    }
}