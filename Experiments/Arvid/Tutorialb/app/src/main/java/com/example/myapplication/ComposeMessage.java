package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ComposeMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);
        Intent i = getIntent();
        String name = i.getStringExtra("NAME");
        TextView tv = findViewById(R.id.textView);
        if (name == null) {
            tv.setText(R.string.cm);
        }
        else {
            tv.setText(name);
        }
    }
}