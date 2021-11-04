package com.example.foodhub.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.foodhub.R;

public class AdminMainActivity extends AppCompatActivity {

    private Bundle bundle;

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Intent P = getIntent();
        bundle = P.getExtras();
        bundle.putString("type", "admin");
        navController = Navigation.findNavController(this, R.id.admin_fragment_main);
        navController.navigate(R.id.ManageFirms, bundle);
    }

}
