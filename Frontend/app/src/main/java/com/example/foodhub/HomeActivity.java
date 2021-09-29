package com.example.foodhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent I = getIntent();
        String firstName = I.getStringExtra("FirstName");
        ((TextView)findViewById(R.id.homepage_username)).setText(firstName);

    }

    public void goToSignInActivity(View v) {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

}