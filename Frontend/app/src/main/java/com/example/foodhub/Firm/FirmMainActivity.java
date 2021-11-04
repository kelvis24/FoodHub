package com.example.foodhub.Firm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.foodhub.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class FirmMainActivity extends AppCompatActivity {

    private Bundle bundle;
    private NavController navController;

    private BottomNavigationView menu;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_main);
        Intent P = getIntent();
        bundle = P.getExtras();
        navController = Navigation.findNavController(this, R.id.firm_fragment_main);
        menu = findViewById(R.id.firm_bottom_navigation_view);
        View manageMenu = findViewById(R.id.manage_categories_option);
        manageMenu.setOnClickListener(this::manageMenu);
        View firmManageOrders = findViewById(R.id.manage_orders_option);
        firmManageOrders.setOnClickListener(this::firmManageOrders);
        manageMenu(manageMenu);
    }

    public void manageMenu(View view) {
        menu.setSelectedItemId(R.id.manage_categories_option);
        navController.navigate(R.id.ManageCategories, bundle);
    }

    public void firmManageOrders(View view) {
        menu.setSelectedItemId(R.id.manage_orders_option);
        navController.navigate(R.id.ManageFirmOrders, bundle);
    }

}