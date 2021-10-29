package com.example.foodhub.Customer.Home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodhub.Class.Company;
import com.example.foodhub.Class.SmallCompany;
import com.example.foodhub.R;
import com.example.foodhub.Recyclers.RecyclerAdapter;
import com.example.foodhub.databinding.ActivityHomeBinding;
import com.example.foodhub.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ActivityHomeBinding originalBinding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        originalBinding = ActivityHomeBinding.inflate(getLayoutInflater());
//        setContentView(originalBinding.getRoot());
//
//        final RecyclerView recyclerView = binding.recyclerView1;
//
//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this.getContext(), getObject());
//        recyclerView.setAdapter(recyclerAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//
//        LoadCompanies();
    }

    private void setContentView(ConstraintLayout root) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.recyclerView1;

       // ArrayList<Object> array = new ArrayList<>();
        //array = (  ArrayList<Object>) homeViewModel.getCompanies();


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this.getContext(), getObject());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;

        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private ArrayList<Object> getObject() {
        ArrayList<Object> arrayList = new ArrayList<>();
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

}