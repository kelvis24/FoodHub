package com.example.foodhub.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodhub.R;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.customer_login_button);
        btn.setOnClickListener(this::goToCustomerLoginActivity);
        btn = findViewById(R.id.firm_login_button);
        btn.setOnClickListener(this::goToFirmLoginActivity);
        btn = findViewById(R.id.admin_login_button);
        btn.setOnClickListener(this::goToAdminLoginActivity);
        btn = findViewById(R.id.customer_sign_up_button);
        btn.setOnClickListener(this::goToSignUpActivity);
    }

    public void goToCustomerLoginActivity(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void goToFirmLoginActivity(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void goToAdminLoginActivity(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void goToSignUpActivity(View v) {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

}