package com.example.foodhub;

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

import java.util.ArrayList;
import com.example.foodhub.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Company> arrayList;
    private ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_home);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
//
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home2, R.id.pickup, R.id.search, R.id.orders, R.id.account)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

       LoadCompanies();

    }
    
    public void LoadCompanies() {
        arrayList= new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView1);

        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "title", "This is a message"));
        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "titles", "C'est un message"));
        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "tit", "enni tie lok"));
        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "dfd", "messages appear here"));
        arrayList.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "titdfle", "still writing here"));


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); 
    }

    public void ClickCompany(View v) {
        //   ImageView btn = (ImageView) findViewById(R.id.ivPost);
        Intent P = new Intent(this, CompanyPage.class);
//        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
//        I.putExtra("Email", email);
        startActivity(P);
    }
}