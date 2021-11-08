package com.example.foodhub.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.foodhub.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Class responsible for creating View
 */
public class CustomerMainActivity extends AppCompatActivity {

    private Bundle bundle;
    private NavController navController;
    private BottomNavigationView menu;

    /**
     * Method responsible for creating View
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        Intent P = getIntent();
        bundle = P.getExtras();
        navController = Navigation.findNavController(this, R.id.customer_fragment_main);
        menu = findViewById(R.id.customer_bottom_navigation_view);
        View browseFirms = findViewById(R.id.browse_firms_option);
        browseFirms.setOnClickListener(this::browseFirms);
        View manageCustomerOrders = findViewById(R.id.manage_customer_orders_option);
        manageCustomerOrders.setOnClickListener(this::manageCustomerOrders);
        View customerAccount = findViewById(R.id.customer_account_option);
        customerAccount.setOnClickListener(this::customerAccount);
        browseFirms(browseFirms);
    }

    /**
     * Method responsible for Loadidng View
     */
    public void browseFirms(View view) {
        menu.setSelectedItemId(R.id.browse_firms_option);
        navController.navigate(R.id.BrowseFirms, bundle);
    }

    /**
     * Method responsible for loading View
     */
    public void manageCustomerOrders(View view) {
        menu.setSelectedItemId(R.id.manage_customer_orders_option);
        navController.navigate(R.id.ManageCustomerOrders, bundle);
    }

    /**
     * Method responsible for loading View
     */
    public void customerAccount(View view) {
        menu.setSelectedItemId(R.id.customer_account_option);
        navController.navigate(R.id.CustomerAccount, bundle);
    }
}