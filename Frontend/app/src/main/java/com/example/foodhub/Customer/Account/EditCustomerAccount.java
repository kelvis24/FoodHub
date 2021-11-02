package com.example.foodhub.Customer.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Customer.Home.Home;
import com.example.foodhub.Customer.Home.HomeActivity;
import com.example.foodhub.R;
import com.example.foodhub.databinding.ActivityEditcustomerpageBinding;
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

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickToCancelEditCustomer(View v) {
        Intent Ph = new Intent(this, HomeActivity.class);
//        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
//        I.putExtra("Email", email);
        startActivity(Ph);
    }

    public void clickToSaveEditedCustomer(View v) {
        Intent Ph = new Intent(this, HomeActivity.class);
//        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
//        I.putExtra("Email", email);
        startActivity(Ph);
    }


}