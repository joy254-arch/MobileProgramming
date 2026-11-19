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

import com.example.loginapplication.LoginActivity;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText emailText, passwordText;

    Pattern lowercase = Pattern.compile("^.*[a-z].*$");
    Pattern uppercase = Pattern.compile("^.*[A-Z].*$");
    Pattern number = Pattern.compile("^.*[0-9].*$");
    Pattern schar = Pattern.compile("^.*[^a-zA-Z0-9].*$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.enableEdgeToEdge(getWindow());

        setContentView(R.layout.activity_main);

        emailText = findViewById(R.id.editTextEmail);
        passwordText = findViewById(R.id.editTextPassword);

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

    public void signup(View view) {

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (!isValidPassword(password)) {

            Toast.makeText(
                    this,
                    "Password does not match the rules",
                    Toast.LENGTH_LONG
            ).show();

            return;
        }

        Intent intent = new Intent(this,LoginActivity.class);

        intent.putExtra("email", email);
        intent.putExtra("password", password);

        startActivity(intent);
    }

    private boolean isValidPassword(String password) {

        if (password.length() < 8) {
            return false;
        }

        if (!lowercase.matcher(password).matches()) {
            return false;
        }

        if (!uppercase.matcher(password).matches()) {
            return false;
        }

        if (!number.matcher(password).matches()) {
            return false;
        }

        if (!schar.matcher(password).matches()) {
            return false;
        }

        return true;
    }
}
