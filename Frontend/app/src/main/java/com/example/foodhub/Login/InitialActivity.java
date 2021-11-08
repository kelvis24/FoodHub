package com.example.foodhub.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodhub.R;

/**
 * Controls the R.layout.activity_initial view
 * @author Arvid Gustafson
 * @see AppCompatActivity
 */
public class InitialActivity extends AppCompatActivity {

    /**
     * Binds the buttons to their respective methods
     * @param savedInstanceState A bundle that is passed in
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        Button btn = findViewById(R.id.customer_login_button);
        btn.setOnClickListener(this::goToCustomerLoginActivity);
        btn = findViewById(R.id.firm_login_button);
        btn.setOnClickListener(this::goToFirmLoginActivity);
        btn = findViewById(R.id.admin_login_button);
        btn.setOnClickListener(this::goToAdminLoginActivity);
        btn = findViewById(R.id.customer_sign_up_button);
        btn.setOnClickListener(this::goToSignUpActivity);
    }

    /**
     * Switches to the login page as a customer upon clicking the "customer login" button
     * @param v The "customer login" button
     */
    public void goToCustomerLoginActivity(View v) {
        Intent I = new Intent(this, LoginActivity.class);
        I.putExtra("type", "customer");
        startActivity(I);
    }

    /**
     * Switches to the login page as a firm upon clicking the "firm login" button
     * @param v The "firm login" button
     */
    public void goToFirmLoginActivity(View v) {
        Intent I = new Intent(this, LoginActivity.class);
        I.putExtra("type", "firm");
        startActivity(I);
    }

    /**
     * Switches to the login page as an admin upon clicking the "admin login" button
     * @param v The "admin login" button
     */
    public void goToAdminLoginActivity(View v) {
        Intent I = new Intent(this, LoginActivity.class);
        I.putExtra("type", "admin");
        startActivity(I);
    }

    /**
     * switches to the sign-in page upon clicking the "sign-in" button
     * @param v The "sign-in" button
     */
    public void goToSignUpActivity(View v) {
        Intent I = new Intent(this, SignUpActivity.class);
        startActivity(I);
    }

}
