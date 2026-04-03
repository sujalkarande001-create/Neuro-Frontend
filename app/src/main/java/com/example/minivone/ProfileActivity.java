package com.example.minivone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ProfileActivity extends AppCompatActivity {

    TextView profileNameText, profileEmailText, profilePhoneText, profileAddressText;
    MaterialButton continueBtn, editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileNameText = findViewById(R.id.profileName);
        profileEmailText = findViewById(R.id.profileEmail);
        profilePhoneText = findViewById(R.id.profilePhone);
        profileAddressText = findViewById(R.id.profileAddress);
        continueBtn = findViewById(R.id.continueBtn);
        editBtn = findViewById(R.id.editBtn);

        // 🔥 Load from SharedPreferences
        PrefManage prefManager = new PrefManage(this);

        profileNameText.setText(prefManager.getName());
        profileEmailText.setText(prefManager.getEmail());
        profilePhoneText.setText(prefManager.getPhone());
        profileAddressText.setText(prefManager.getAddress());

        // Continue button
        continueBtn.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
            finish();
        });
        editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, ProfileDataActivity.class);
            startActivity(intent);
        });
    }
}