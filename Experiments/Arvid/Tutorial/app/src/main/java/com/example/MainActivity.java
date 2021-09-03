package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(this::launchSettings);
    }

    public void launchSettings(View v) {
        Intent i = new Intent(this, SettingsActivity.class);
        EditText et = findViewById(R.id.textbox);
        String message = et.getText().toString();
        i.putExtra("COOL", message);
        startActivity(i);
    }

}