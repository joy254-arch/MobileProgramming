package com.example.loginapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText emailText, passwordText;
    String rEmail, rPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.enableEdgeToEdge(getWindow());

        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.editTextEmail);
        passwordText = findViewById(R.id.editTextPassword);

        rEmail = getIntent().getStringExtra("email");
        rPassword = getIntent().getStringExtra("password");

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });
    }

    public void login(View view) {

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (rEmail != null && rPassword != null &&
                rEmail.equals(email) && rPassword.equals(password)) {

            Intent intent = new Intent(
                    this, LoginSuccessfulActivity.class);

            startActivity(intent);

        } else {

            Toast.makeText(
                    this,
                    "Invalid Credentials",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
