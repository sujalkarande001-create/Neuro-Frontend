package com.example.minivone;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button loginButton;
    FirebaseAuth mAuth;
    PrefManage prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginBtn);
        TextView signup = findViewById(R.id.goToSignupText);

        mAuth = FirebaseAuth.getInstance();
        prefManager = new PrefManage(this);

        // ❌ REMOVE AUTO LOGIN BLOCK (IMPORTANT)

        loginButton.setOnClickListener(v -> {

            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                            // ✅ CORRECT FLOW
                            if (!prefManager.isProfileCompleted()) {
                                startActivity(new Intent(LoginActivity.this, ProfileDataActivity.class));
                            } else {
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            }

                            finish();

                        } else {
                            Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        signup.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });
    }
}