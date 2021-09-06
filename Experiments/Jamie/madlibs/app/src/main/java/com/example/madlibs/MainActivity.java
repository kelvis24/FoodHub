package com.example.madlibs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView Title;
    private TextView Given;
    private EditText Words;
    private Button Go;
    private TextView Instruct;
    private Button Instructions;
    private String[] typeOfWords = {"adjective", "adjective", "food", "type of room",
            "part-tense verb", "verb", "type of relative", "noun", "liquid", "verb ending in -ing",
            "plural body part", "plural noun", "verb ending in -ing", "noun"};
    private String[] UserWords = new String[typeOfWords.length];
    private int counter = 0;
    private TextView Story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Title = (TextView)findViewById(R.id.tvTitle);
        Given = (TextView)findViewById(R.id.tvGive);
        Given.setText("");
        Words = (EditText)findViewById(R.id.ptWord);
        Go = (Button)findViewById(R.id.bGo);
        Instructions = (Button)findViewById(R.id.bInstruct);
        Instruct = (TextView)findViewById(R.id.tvInstruct);
        Story = (TextView)findViewById(R.id.tvStory);
        Story.setText("");

        Instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Instruct.setText("Think of a word that matches the type in the box, type it in, " +
                        "then press the button! Do this until no more prompts come up and enjoy your MadLibs story!");
            }
        });

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Instruct.setText(""); //remove instructions that may be present
                UserWords[counter] = Words.getText().toString();
                Words.setText(typeOfWords[counter + 1] + "!");
                counter++;

                //Erase text features and buttons to make room for story
                if (counter == typeOfWords.length - 1) {
                    Given.setText("");
                    Words.setText("");
                    Title.setText("");
                    Instructions.setVisibility(View.INVISIBLE);
                    Go.setVisibility(View.INVISIBLE);
                    Story.setText(storyTime(UserWords));
                }
            }
        });

        Given.setText(storyTime(UserWords));
    }

    private String storyTime(String[] arr){
        return "It was a(n) " + arr[0] + ", cold November day. I woke up to the " + arr[1] + " smell of " + arr[2] + " cooking in " +
                "the " + arr[3] + " downstairs. I " + arr[4] + " down the stairs to see if I could help " + arr[5] + " the dinner. " +
                "My mom said \"See if " + arr[6] + " needs a fresh " + arr[7] + ".\" So I carried a tray of glass full of " + arr[8] + " into " +
                "the " + arr[9] + " room. When I got there, I couldn't believe my " + arr[10] + ". There were " + arr[11] + " " + arr[12] + " on " +
                "the " + arr[13] + "!";
    }
}