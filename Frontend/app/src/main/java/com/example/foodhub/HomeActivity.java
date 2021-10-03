package com.example.foodhub;

import static com.example.foodhub.R.id.bottomNavigationView;
import static com.example.foodhub.R.id.recyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Company> arrayList;
 //   private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home, R.id.pickup, R.id.search, R.id.orders, R.id.account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



        arrayList= new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "title", "This is a message"));
        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "titles", "C'est un message"));
        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "tit", "enni tie lok"));
        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "dfd", "messages appear here"));
        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "titdfle", "still writing here"));


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent I = getIntent();
        String name = I.getStringExtra("Email");




    }
}