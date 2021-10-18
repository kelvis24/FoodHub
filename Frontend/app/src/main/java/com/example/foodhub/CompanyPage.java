package com.example.foodhub;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.foodhub.Company;
import com.example.foodhub.databinding.ActivityFullComapnypageViewBinding;
import com.example.foodhub.databinding.ActivityHomeBinding;
import com.example.foodhub.databinding.FragmentPickupBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CompanyPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Company> arrayList;
    private ActivityFullComapnypageViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_full_comapnypage_view);

        binding = ActivityFullComapnypageViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home2, R.id.pickup, R.id.search, R.id.orders, R.id.account)
                .build();

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);


    }

    public void loginSomething(View v) {
        int i = 0;
    }
}