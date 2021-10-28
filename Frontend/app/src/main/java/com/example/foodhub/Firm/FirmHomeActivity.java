package com.example.foodhub.Firm;

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
import com.example.foodhub.databinding.FirmMainactivityBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class FirmHomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Object> arrayList;
    private FirmMainactivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.firm_mainactivity);

        binding = FirmMainactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
        BottomNavigationView navView = findViewById(R.id.firmbottomNavigationView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.firm_home, R.id.firm_menupage, R.id.firm_account)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_firmactivity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.firmbottomNavigationView, navController);
//
//        LoadCompanies();

    }

    public void ClickToSaveMenuItem(View v) {
        Intent Ph = new Intent(this, FirmMenuItemActivity.class);
//        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
//        I.putExtra("Email", email);
        startActivity(Ph);
    }


}