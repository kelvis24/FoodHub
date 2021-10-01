package com.example.foodhub;

import static com.example.foodhub.R.id.recyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Company> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        arrayList= new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "title", "message"));
        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "titles", "message"));
        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "tit", "message"));
        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "dfd", "message"));
        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "titdfle", "message"));


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent I = getIntent();
        String name = I.getStringExtra("Email");
      //  ((TextView)findViewById(R.id.homepage_username)).setText(name);

    }
}