package com.example.nac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WinScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);
        Intent intent = getIntent();
        String winner = intent.getStringExtra("WINNER");
        ImageView winImage = findViewById(R.id.WinImage);
        if (winner.equals("0") || winner.equals("1")) {
            winImage.setImageResource(winner.equals("1") ? R.drawable.cross : R.drawable.naught);
            TextView text = findViewById(R.id.WinText);
            text.setText(winner.equals("1") ? R.string.c_win : R.string.n_win);
        }
        Button button = findViewById(R.id.MenuButton2);
        button.setOnClickListener(this::menu);
    }

    void menu(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}