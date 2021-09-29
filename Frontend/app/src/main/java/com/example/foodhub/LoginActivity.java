package com.example.foodhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v) {
        Button btn = (Button) findViewById(R.id.login_button);
        Intent I = new Intent(this, HomeActivity.class);

        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();

        I.putExtra("Email", email);

        startActivity(I);
    }
}