package com.example.foodhub.Customer.Search;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.databinding.FragmentSearchBinding;

import java.util.ArrayList;






public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Object> arrayList;
    private FragmentSearchBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_home);

        binding = FragmentSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//        BottomNavigationView navView = findViewById(R.id.firmbottomNavigationView);
////
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.FirmHome, R.id.MenuPage, R.id.FirmAccount)
//                .build();

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
//
//        LoadCompanies();

    }
}
