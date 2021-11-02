package com.example.foodhub.Customer.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Class.Menu;
import com.example.foodhub.Customer.Account.Account;
import com.example.foodhub.Customer.Account.EditCustomerAccount;
import com.example.foodhub.Program.ProgramController;
import com.example.foodhub.R;
import com.example.foodhub.databinding.ActivityViewthismenuBinding;

import java.util.ArrayList;


import java.util.ArrayList;

public class FoodItemPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Object> arrayList;
    private ActivityViewthismenuBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_home);

        binding = ActivityViewthismenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }


    public void clickToAddOrder(View v) {
        Intent Ph = new Intent(this, CompanyPage.class);
//        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
//        I.putExtra("Email", email);

//        ProgramController.Order.initialize();
        ProgramController.MenuOrders.add(new Menu(R.drawable.wasabilogo, "death vpoiuytryalley", "usme", "653"));
        startActivity(Ph);
    }

    public void ClickToUnseeMenuItem(View v) {
        Intent Ph = new Intent(this, CompanyPage.class);
//        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
//        I.putExtra("Email", email);
        startActivity(Ph);
    }


}