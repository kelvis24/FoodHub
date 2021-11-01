package com.example.foodhub.Customer.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.R;
import com.example.foodhub.databinding.ActivityEditcustomerpageBinding;
import com.example.foodhub.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class EditCustomerAccount extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Object> arrayList;
    private ActivityEditcustomerpageBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_home);

        binding = ActivityEditcustomerpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
////
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.home2,
//                //  R.id.pickup, R.id.search,
//                R.id.orders, R.id.account)
//                .build();
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        //LoadCompanies();
    }

    public void clickToCancelEditCustomer(View v) {
        Intent Ph = new Intent(this, Account.class);
//        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
//        I.putExtra("Email", email);
        startActivity(Ph);
    }
    public void clickToSaveEditedCustomer(View v) {
        Intent Ph = new Intent(this, Account.class);
//        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
//        I.putExtra("Email", email);
        startActivity(Ph);
    }


}