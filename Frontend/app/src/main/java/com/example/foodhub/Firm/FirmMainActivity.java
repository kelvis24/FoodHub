package com.example.foodhub.Firm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.foodhub.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * The controller for the R.layout.activity_firm_main view
 * @author Arvid Gustafson
 * @see AppCompatActivity
 */
public class FirmMainActivity extends AppCompatActivity {

    private Bundle bundle;
    private NavController navController;

    private BottomNavigationView menu;

    /**
     * Receives bundle contents and binds menu options upon creation
     * @param savedInstanceState a bundle passed in
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_main);
        Intent P = getIntent();
        bundle = P.getExtras();
        navController = Navigation.findNavController(this, R.id.firm_fragment_main);
        menu = findViewById(R.id.firm_bottom_navigation_view);
        View manageMenu = findViewById(R.id.manage_categories_option);
        manageMenu.setOnClickListener(this::manageMenu);
        View manageFirmOrders = findViewById(R.id.manage_firm_orders_option);
        manageFirmOrders.setOnClickListener(this::manageFirmOrders);
        View firmAccount = findViewById(R.id.firm_account_option);
        firmAccount.setOnClickListener(this::firmAccount);
        manageMenu(manageMenu);
    }

    /**
     * Navigates to the "manage menu" (manage categories) fragment once
     *      the corresponding menu option is selected
     * @param view The corresponding menu option
     */
    public void manageMenu(View view) {
        menu.setSelectedItemId(R.id.manage_categories_option);
        navController.navigate(R.id.ManageCategories, bundle);
    }

    /**
     * Navigates to the "manage firm orders" fragment once
     *      the corresponding menu option is selected
     * @param view The corresponding menu option
     */
    public void manageFirmOrders(View view) {
        menu.setSelectedItemId(R.id.manage_firm_orders_option);
        navController.navigate(R.id.ManageFirmOrders, bundle);
    }

    /**
     * Navigates to the "firm account" fragment once
     *      the corresponding menu option is selected
     * @param view The corresponding menu option
     */
    public void firmAccount(View view) {
        menu.setSelectedItemId(R.id.firm_account_option);
        navController.navigate(R.id.FirmAccount, bundle);
    }

}