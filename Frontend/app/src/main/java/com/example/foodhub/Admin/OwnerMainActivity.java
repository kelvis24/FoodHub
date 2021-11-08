package com.example.foodhub.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.foodhub.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OwnerMainActivity extends AppCompatActivity {

    private Bundle bundle;
    private NavController navController;

    private BottomNavigationView menu;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main);
        Intent P = getIntent();
        bundle = P.getExtras();
        bundle.putString("type", "owner");
        navController = Navigation.findNavController(this, R.id.owner_fragment_main);
        menu = findViewById(R.id.owner_bottom_navigation_view);
        View manageAdmins = findViewById(R.id.manage_admins_option);
        manageAdmins.setOnClickListener(this::manageAdmins);
        View manageFirms = findViewById(R.id.manage_firms_option);
        manageFirms.setOnClickListener(this::manageFirms);
        View ownerAccount = findViewById(R.id.owner_account_option);
        ownerAccount.setOnClickListener(this::ownerAccount);
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

    public void ownerAccount(View view) {
        menu.setSelectedItemId(R.id.owner_account_option);
        navController.navigate(R.id.OwnerAccount, bundle);
    }

}
