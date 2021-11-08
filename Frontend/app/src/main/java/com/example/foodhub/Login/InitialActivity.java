package com.example.foodhub.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodhub.R;

/**
 * The Initial activity when activating the frontend of FoodHub.
 * @author 1_CW_2
 *
 */

public class InitialActivity extends AppCompatActivity {

    
    /** Creates an initial instance that displays the on screen with all given buttons present.
    * @param savedInstanceState bundle of a saved instance brought up on creation
    * @return NULL
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    /** Sends information to the loginactivity with the type of user.
    * @param v View
    * @see LoginActivity
    */
    public void goToCustomerLoginActivity(View v) {
        Intent I = new Intent(this, LoginActivity.class);
        I.putExtra("type", "customer");
        startActivity(I);
    }
    /** Sends information to the loginactivity with the type of firm.
    * @param v View
    * @see LoginActivity
    */
    public void goToFirmLoginActivity(View v) {
        Intent I = new Intent(this, LoginActivity.class);
        I.putExtra("type", "firm");
        startActivity(I);
    }
    /** Sends information to the loginactivity with the type of admin.
    * @param v View
    * @see LoginActivity
    */
    public void goToAdminLoginActivity(View v) {
        Intent I = new Intent(this, LoginActivity.class);
        I.putExtra("type", "admin");
        startActivity(I);
    }
    /** Sends information to the SignUpActivity to add in new information.
    * @param v View
    * @see SignUpActivity
    */
    public void goToSignUpActivity(View v) {
        Intent I = new Intent(this, SignUpActivity.class);
        startActivity(I);
    }

}
