package com.example.foodhub.Customer.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import com.example.foodhub.Admin.AdminHome.AdminHomeActivity;
import com.example.foodhub.Customer.Account.Account;
import com.example.foodhub.Login.LoginActivity;
import com.example.foodhub.Class.Company;
import com.example.foodhub.Customer.Account.EditCustomerAccount;
import com.example.foodhub.Firm.FirmHomeActivity;
import com.example.foodhub.Program.ProgramController;
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
                R.id.home2, R.id.orders, R.id.account).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

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
        Intent P = new Intent(this, CompanyPage.class);
        startActivity(P);
    }


    public void clickToSeeEditCustomerPages(View v) {
        Intent Ph = new Intent(this, EditCustomerAccount.class);
        startActivity(Ph);
    }




}