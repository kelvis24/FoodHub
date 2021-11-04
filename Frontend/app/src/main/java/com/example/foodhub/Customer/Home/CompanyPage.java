package com.example.foodhub.Customer.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.foodhub.Common.Menu;
import com.example.foodhub.R;
import com.example.foodhub.Recyclers.MenuRecyclerAdapter;
import com.example.foodhub.databinding.ActivityFullComapnypageViewBinding;

import java.util.ArrayList;

public class CompanyPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Object> arrayList;
    private ActivityFullComapnypageViewBinding binding;
//    private ActivityHome2Binding binding1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_full_comapnypage_view);

        binding = ActivityFullComapnypageViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //        binding1 = ActivityHome2Binding.inflate(getLayoutInflater());
//        setContentView(binding1.getRoot());


        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


//        BottomNavigationView navView = findViewById(R.id.bottomNavigationView2);

//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.home2,
//           //     R.id.pickup, R.id.search,
//                R.id.orders, R.id.account)
//                .build();
//
//   NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding1.bottomNavigationView2, navController);

          LoadMenu();
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


    public void LoadMenu() {
        arrayList= new ArrayList<Object>();
        recyclerView = findViewById(R.id.recyclerView_menu_view);

        arrayList.add( new Menu(R.drawable.litramen, "Lit Ramen", "This is a descyuription of lit ramen", "1000"));
        arrayList.add( new Menu(R.drawable.litramen, "Lit Ramen", "This is a desclikujyhyription of lit ramen", "1000"));
        arrayList.add( new Menu(R.drawable.litramen, "Lit Ramen", "This is a deuiytscription of lit ramen", "1000"));
        arrayList.add( new Menu(R.drawable.litramen, "Lit Ramen", "This is a de=ramen", "1000"));


        MenuRecyclerAdapter recyclerAdapter = new MenuRecyclerAdapter(arrayList);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void clickToSeeThatMenu(View v) {
        Intent Ph = new Intent(this, FoodItemPage.class);
        startActivity(Ph);
    }
}