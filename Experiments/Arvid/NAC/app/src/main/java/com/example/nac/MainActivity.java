package com.example.nac;

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
        Button btn = findViewById(R.id.LocalButton);
        btn.setOnClickListener(this::local_game);
    }

    public void local_game(View v) {
        Intent i = new Intent(this, LocalGame.class);
        startActivity(i);
    }

}