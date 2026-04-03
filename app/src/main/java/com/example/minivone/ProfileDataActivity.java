package com.example.minivone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileDataActivity extends AppCompatActivity {

    EditText nameInput, phoneInput, emailInput, addressInput;
    Button saveBtn;
    PrefManage prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data);

        prefManager = new PrefManage(this);

        nameInput = findViewById(R.id.nameInput);
        phoneInput = findViewById(R.id.phoneInput);
        emailInput = findViewById(R.id.emailInput);
        addressInput = findViewById(R.id.addressInput);
        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(v -> {

            String name = nameInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String address = addressInput.getText().toString().trim();

            // 🔥 Save data permanently
            prefManager.saveUserData(name, email, phone, address);
            prefManager.setProfileCompleted(true);

            startActivity(new Intent(ProfileDataActivity.this, ProfileActivity.class));
            finish();
        });
    }
}