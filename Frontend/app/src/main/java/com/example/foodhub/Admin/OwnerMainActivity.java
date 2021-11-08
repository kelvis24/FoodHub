package com.example.foodhub.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.foodhub.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * The controller for the R.layout.activity_owner_main view
 * @author Arvid Gustafson
 * @see AppCompatActivity
 */
public class OwnerMainActivity extends AppCompatActivity {

    private Bundle bundle;
    private NavController navController;

    private BottomNavigationView menu;

    /**
     * Receives bundle contents and binds menu options upon creation
     * @param savedInstanceState a bundle passed in
     */
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

    /**
     * Navigates to the "manage admins" fragment once the corresponding
     *      menu option is selected
     * @param view The corresponding menu option
     */
    public void manageAdmins(View view) {
        menu.setSelectedItemId(R.id.manage_admins_option);
        navController.navigate(R.id.ManageAdmins, bundle);
    }

    /**
     * Navigates to the "manage firms" fragment once the corresponding
     *      menu option is selected
     * @param view The corresponding menu option
     */
    public void manageFirms(View view) {
        menu.setSelectedItemId(R.id.manage_firms_option);
        navController.navigate(R.id.ManageFirms, bundle);
    }

    /**
     * Navigates to the "admin account" fragment once the corresponding
     *      menu option is selected
     * @param view The corresponding menu option
     */
    public void ownerAccount(View view) {
        menu.setSelectedItemId(R.id.owner_account_option);
        navController.navigate(R.id.OwnerAccount, bundle);
    }

}
