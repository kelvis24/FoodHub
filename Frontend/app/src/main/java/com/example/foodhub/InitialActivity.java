package com.example.foodhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.customer_sign_up_button);
        btn.setOnClickListener(this::goToSignUpActivity);
    }

    public void goToSignInActivity(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void goToSignUpActivity(View v) {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

}