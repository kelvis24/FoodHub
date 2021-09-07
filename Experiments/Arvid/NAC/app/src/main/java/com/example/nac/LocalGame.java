package com.example.nac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class LocalGame extends AppCompatActivity {

    protected int[] board;
    protected int turn;
    protected ImageView turn_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_game);
        turn_image = findViewById(R.id.TurnImage);
        board = new int[9];
        for (int i = 0; i < 9; i++)
            board[i] = 2;
        turn = 1;
        turn_image.setImageResource(R.drawable.cross);
        ImageButton btn;
        btn = findViewById(R.id.space1);
        btn.setOnClickListener(this::turn);
        btn = findViewById(R.id.space2);
        btn.setOnClickListener(this::turn);
        btn = findViewById(R.id.space3);
        btn.setOnClickListener(this::turn);
        btn = findViewById(R.id.space4);
        btn.setOnClickListener(this::turn);
        btn = findViewById(R.id.space5);
        btn.setOnClickListener(this::turn);
        btn = findViewById(R.id.space6);
        btn.setOnClickListener(this::turn);
        btn = findViewById(R.id.space7);
        btn.setOnClickListener(this::turn);
        btn = findViewById(R.id.space8);
        btn.setOnClickListener(this::turn);
        btn = findViewById(R.id.space9);
        btn.setOnClickListener(this::turn);
        Button button = findViewById(R.id.MenuButton1);
        button.setOnClickListener(this::menu);
    }

    void turn(View v) {
        int index = 0;
        ImageButton btn = (ImageButton) v;
             if (btn.getId() == R.id.space2) index = 1;
        else if (btn.getId() == R.id.space3) index = 2;
        else if (btn.getId() == R.id.space4) index = 3;
        else if (btn.getId() == R.id.space5) index = 4;
        else if (btn.getId() == R.id.space6) index = 5;
        else if (btn.getId() == R.id.space7) index = 6;
        else if (btn.getId() == R.id.space8) index = 7;
        else if (btn.getId() == R.id.space9) index = 8;
        if (board[index] != 2)
            return;
        board[index] = turn;
        btn.setImageResource(turn == 1 ? R.drawable.cross : R.drawable.naught);
        if (check_win(turn)) {
            String str = turn == 1 ? "1" : "0";
            Intent intent = new Intent(this, WinScreen.class);
            intent.putExtra("WINNER",str);
            startActivity(intent);
        }
        if (check_draw()) {
            String str = "2";
            Intent intent = new Intent(this, WinScreen.class);
            intent.putExtra("WINNER",str);
            startActivity(intent);
        }
        turn = turn == 1 ? 0 : 1;
        turn_image.setImageResource(turn == 1 ? R.drawable.cross : R.drawable.naught);
    }

    boolean check_win(int val) {
        return (board[0]==val&&board[1]==val&&board[2]==val)||(board[0]==val&&board[3]==val&&board[6]==val)||(board[0]==val&&board[4]==val&&board[8]==val)
             ||(board[3]==val&&board[4]==val&&board[5]==val)||(board[1]==val&&board[4]==val&&board[7]==val)||(board[2]==val&&board[4]==val&&board[6]==val)
             ||(board[6]==val&&board[7]==val&&board[8]==val)||(board[2]==val&&board[5]==val&&board[8]==val);
    }

    boolean check_draw() {
        return (board[0]!=2&&board[1]!=2&&board[2]!=2&&board[3]!=2&&board[4]!=2&&board[5]!=2&&board[6]!=2&&board[7]!=2&&board[8]!=2);
    }

    void menu(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}