package com.example.foodhub.Admin.AdminHome;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.R;
import com.example.foodhub.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class AdminHomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Object> arrayList;
    private ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_admin_home);

//        binding = ActivityHomeBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
////
//        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
////
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.home2,
//                R.id.orders, R.id.account)
//                .build();
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);


    }
}
