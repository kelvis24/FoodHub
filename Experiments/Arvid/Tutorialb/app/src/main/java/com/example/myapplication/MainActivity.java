package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn;
        btn = findViewById(R.id.button1);
        btn.setOnClickListener(this::composeMessage);
        btn = findViewById(R.id.button2);
        btn.setOnClickListener(this::composeMessage);
        btn = findViewById(R.id.button3);
        btn.setOnClickListener(this::composeMessage);
    }

    public void composeMessage(View v) {
        Button btn = (Button) v;
        Intent i = new Intent(this, ComposeMessage.class);
        String name = btn.getText().toString();
        i.putExtra("NAME", name);
        startActivity(i);
    }

}