package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button1);
        btn.setOnClickListener(this::disable);
    }

    public void disable(View v) {
        View myView = findViewById(R.id.button1);
        myView.setEnabled(false);
        Button button = (Button) myView;
        button.setText(R.string.New_Disabled);
    }

}