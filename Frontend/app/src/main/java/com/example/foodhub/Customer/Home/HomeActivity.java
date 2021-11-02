package com.example.foodhub.Customer.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import com.example.foodhub.Admin.AdminHome.AdminHomeActivity;
import com.example.foodhub.Class.Company;
import com.example.foodhub.Firm.FirmHomeActivity;
import com.example.foodhub.R;
import com.example.foodhub.Recyclers.RecyclerAdapter;
import com.example.foodhub.Class.SmallCompany;
import com.example.foodhub.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Object> arrayList;
    private ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_home);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
//
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home2,
              //  R.id.pickup, R.id.search,
                R.id.orders, R.id.account)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

       //LoadCompanies();

    }
    
    public void LoadCompanies() {
        arrayList= new ArrayList<Object>();
        recyclerView = findViewById(R.id.recyclerView1);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, this.getObject());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); 
    }

    private ArrayList<Object> getObject() {
        arrayList.add(getVerticalData().get(0));
        arrayList.add(getHorizontalData().get(0));
        arrayList.add(getVerticalData().get(0));
        return arrayList;
    }

    public static ArrayList<Company> getVerticalData() {
        ArrayList<Company> company = new ArrayList<>();
        company.add( new Company(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        company.add( new Company(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        company.add( new Company(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        return company;
    }

    public static ArrayList<SmallCompany> getHorizontalData() {
        ArrayList<SmallCompany> company = new ArrayList<>();
        company.add( new SmallCompany(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        company.add( new SmallCompany(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        company.add( new SmallCompany(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        company.add( new SmallCompany(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        return company;
    }

    public void ClickCompany(View v) {
        //   ImageView btn = (ImageView) findViewById(R.id.ivPost);
        Intent P = new Intent(this, CompanyPage.class);
//        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
//        I.putExtra("Email", email);
        startActivity(P);
    }

    public void ClickToSeeFirmAccount(View v) {
        Intent Ph = new Intent(this, FirmHomeActivity.class);
//        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
//        I.putExtra("Email", email);
        startActivity(Ph);
    }

    public void clickToSeeAdminPages(View v) {
        Intent Ph = new Intent(this, AdminHomeActivity.class);
//        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
//        I.putExtra("Email", email);
        startActivity(Ph);
    }


}