package com.example.foodhub.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.foodhub.R;
import com.example.foodhub.databinding.ActivityOwnerMainBinding;

public class OwnerMainActivity extends AppCompatActivity {

    private String username;
    private String password;
    private ActivityOwnerMainBinding binding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOwnerMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppBarConfiguration config = new AppBarConfiguration.Builder(
                R.id.manage_admins, R.id.manage_firms).build();
        NavController navController = Navigation.findNavController(this, R.id.owner_fragment_main);
        NavigationUI.setupActionBarWithNavController(this, navController, config);
        NavigationUI.setupWithNavController(binding.ownerBottomNavigationView, navController);
        Intent P = getIntent();
        username = P.getStringExtra("username");
        password = P.getStringExtra("password");
    }

}
