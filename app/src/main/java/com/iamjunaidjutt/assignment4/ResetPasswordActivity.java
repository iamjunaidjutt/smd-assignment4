package com.iamjunaidjutt.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetButton;
    private TextView statusMessage;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        mAuth = FirebaseAuth.getInstance();


        emailEditText = findViewById(R.id.emailEditText);
        resetButton = findViewById(R.id.resetButton);
        statusMessage = findViewById(R.id.statusMessage);


        resetButton.setOnClickListener(v -> resetPassword());
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();


        if (email.isEmpty()) {
            showErrorMessage("Please enter your email.");
            return;
        }


        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        showSuccessMessage("Password reset email sent. Please check your inbox.");
                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        showErrorMessage("Failed to send password reset email: " + task.getException().getMessage());
                    }
                });
    }

    private void showErrorMessage(String message) {
        statusMessage.setVisibility(View.VISIBLE);
        statusMessage.setText(message);
        statusMessage.setTextColor(getResources().getColor(android.R.color.holo_red_light));
    }

    private void showSuccessMessage(String message) {
        statusMessage.setVisibility(View.VISIBLE);
        statusMessage.setText(message);
        statusMessage.setTextColor(getResources().getColor(android.R.color.holo_green_light));
    }
}
