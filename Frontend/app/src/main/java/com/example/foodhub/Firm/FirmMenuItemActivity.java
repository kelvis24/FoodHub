package com.example.foodhub.Firm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Company;
import com.example.foodhub.CompanyPage;
import com.example.foodhub.Menu;
import com.example.foodhub.MenuRecyclerAdapter;
import com.example.foodhub.R;
import com.example.foodhub.RecyclerAdapter;
import com.example.foodhub.SmallCompany;
import com.example.foodhub.databinding.FirmMainactivityBinding;
import com.example.foodhub.databinding.FragmentFirmMenupageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FirmMenuItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Object> arrayList;
    private FragmentFirmMenupageBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentFirmMenupageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        BottomNavigationView navView = findViewById(R.id.firmbottomNavigationView);
//
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.firm_home, R.id.firm_menupage, R.id.firm_account)
//                .build();
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_firmactivity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.firmbottomNavigationView, navController);
          LoadMenu();
    }

    public void LoadMenu() {
        arrayList= new ArrayList<Object>();
        recyclerView = findViewById(R.id.recyclerViewFirmMenus);

        arrayList.add( new Menu(R.drawable.litramen, "Lit Ramen", "This is a description of lit ramen", "1000"));
        arrayList.add( new Menu(R.drawable.litramen, "Lit Ramen", "This is a description of lit ramen", "1000"));
        arrayList.add( new Menu(R.drawable.litramen, "Lit Ramen", "This is a description of lit ramen", "1000"));
        arrayList.add( new Menu(R.drawable.litramen, "Lit Ramen", "This is a description of lit ramen", "1000"));


        MenuRecyclerAdapter recyclerAdapter = new MenuRecyclerAdapter(arrayList);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}