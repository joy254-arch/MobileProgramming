package com.example.callapplication;

import android.os.Bundle;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button clearBtn, callBtn, saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextPhone);
        clearBtn = findViewById(R.id.btn_clear);
        callBtn = findViewById(R.id.btn_call);
        saveBtn = findViewById(R.id.btn_save);

        clearBtn.setOnClickListener(v -> editText.setText(""));

        callBtn.setOnClickListener(v -> {
            String number = editText.getText().toString();

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(android.net.Uri.parse("tel:" + number));

            startActivity(intent);
        });

        saveBtn.setOnClickListener(v -> {
            String number = editText.getText().toString();

            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, number);

            startActivity(intent);
        });
    }

    public void inputNumber(View view) {
        Button btn = (Button) view;

        String digit = btn.getText().toString();
        String phoneNumber = editText.getText().toString();

        editText.setText(phoneNumber + digit);
    }
}