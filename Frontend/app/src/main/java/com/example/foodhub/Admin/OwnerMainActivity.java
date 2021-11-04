package com.example.foodhub.Admin;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.foodhub.R;
import com.example.foodhub.databinding.ActivityOwnerMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class OwnerMainActivity extends AppCompatActivity {

    private Bundle bundle;
    private NavController navController;
    private ActivityOwnerMainBinding binding;
    private BottomNavigationItemView manageAdmins;
    private BottomNavigationItemView manageFirms;
    private BottomNavigationView menu;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOwnerMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent P = getIntent();
        bundle = P.getExtras();
        navController = Navigation.findNavController(this, R.id.owner_fragment_main);
        manageAdmins = findViewById(R.id.manage_admins_option);
        manageAdmins.setOnClickListener(this::manageAdmins);
        manageFirms = findViewById(R.id.manage_firms_option);
        manageFirms.setOnClickListener(this::manageFirms);
        menu = findViewById(R.id.owner_bottom_navigation_view);
        manageAdmins(manageAdmins);
    }

    public void manageAdmins(View view) {
        menu.setSelectedItemId(R.id.manage_admins_option);
        navController.navigate(R.id.ManageAdmins, bundle);
    }

    public void manageFirms(View view) {
        menu.setSelectedItemId(R.id.manage_firms_option);
        navController.navigate(R.id.ManageFirms, bundle);
    }

}
