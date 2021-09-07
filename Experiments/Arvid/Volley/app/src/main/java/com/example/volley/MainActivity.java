package com.example.volley;

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
        btn = findViewById(R.id.string_button);
        btn.setOnClickListener(this::to_string);
        btn = findViewById(R.id.json_button);
        btn.setOnClickListener(this::to_json);
        btn = findViewById(R.id.image_button);
        btn.setOnClickListener(this::to_image);
    }

    public void to_string(View v) {
        Intent intent = new Intent(this, StringRequestActivity.class);
        startActivity(intent);
    }

    public void to_json(View v) {
        Intent intent = new Intent(this, JsonRequestActivity.class);
        startActivity(intent);
    }

    public void to_image(View v) {
        Intent intent = new Intent(this, ImageRequestActivity.class);
        startActivity(intent);
    }

}