package com.example.foodhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button btn = findViewById(R.id.add_customer_button);
        btn.setOnClickListener(this::signUp);
    }

    public void signUp(View v) {
        Button btn = (Button) findViewById(R.id.add_customer_button);
        Intent I = new Intent(this, HomeActivity.class);

        String name = ((EditText)findViewById(R.id.sign_up_name_field)).getText().toString();
        String email = ((EditText)findViewById(R.id.sign_up_email_field)).getText().toString();
        String location = ((EditText)findViewById(R.id.sign_up_location_field)).getText().toString();

        I.putExtra("Name", name);
        I.putExtra("Email", email);
        I.putExtra("Location", location);

        startActivity(I);
    }

}
