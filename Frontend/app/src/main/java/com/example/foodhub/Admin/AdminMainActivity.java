package com.example.foodhub.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.foodhub.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * The controller for the R.layout.activity_admin_main view
 * @author Arvid Gustafson
 * @see AppCompatActivity
 */
public class AdminMainActivity extends AppCompatActivity {

    private Bundle bundle;
    private NavController navController;

    private BottomNavigationView menu;

    /**
     * Receives bundle contents and binds menu options upon creation
     * @param savedInstanceState a bundle passed in
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Intent P = getIntent();
        bundle = P.getExtras();
        bundle.putString("type", "admin");
        navController = Navigation.findNavController(this, R.id.admin_fragment_main);
        menu = findViewById(R.id.admin_bottom_navigation_view);
        View manageFirms = findViewById(R.id.admin_firms_option);
        manageFirms.setOnClickListener(this::manageFirms);
        View adminAccount = findViewById(R.id.admin_account_option);
        adminAccount.setOnClickListener(this::adminAccount);
        manageFirms(manageFirms);
    }

    /**
     * Navigates to the "manage firms" fragment once the corresponding
     *      menu option is selected
     * @param view The corresponding menu option
     */
    public void manageFirms(View view) {
        menu.setSelectedItemId(R.id.admin_firms_option);
        navController.navigate(R.id.ManageFirms, bundle);
    }

    /**
     * Navigates to the "admin account" fragment once the corresponding
     *      menu option is selected
     * @param view The corresponding menu option
     */
    public void adminAccount(View view) {
        menu.setSelectedItemId(R.id.admin_account_option);
        navController.navigate(R.id.AdminAccount, bundle);
    }

}
