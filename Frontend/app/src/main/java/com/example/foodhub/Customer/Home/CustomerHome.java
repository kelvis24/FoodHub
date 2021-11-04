package com.example.foodhub.Customer.Home;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodhub.Common.Firm;
import com.example.foodhub.Common.Menu;
import com.example.foodhub.Common.SmallCompany;
import com.example.foodhub.R;
import com.example.foodhub.Recyclers.MenuRecyclerAdapter;
import com.example.foodhub.Recyclers.RecyclerAdapter;
import com.example.foodhub.databinding.ActivityFullComapnypageViewBinding;
import com.example.foodhub.databinding.ActivityHomeBinding;
import com.example.foodhub.databinding.FragmentHomeBinding;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private HomeViewModel homeViewModel;
    private ActivityFullComapnypageViewBinding binding1;
    private FragmentHomeBinding binding;
    private ActivityHomeBinding originalBinding;
    private static int determine = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerHome() {
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
    public static CustomerHome newInstance(String param1, String param2) {
        CustomerHome fragment = new CustomerHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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
        return arrayList;
    }

    public static ArrayList<Firm> getVerticalData() {
        ArrayList<Firm> company = new ArrayList<>();
        // company.add( new Firm(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        // company.add( new Firm(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        // company.add( new Firm(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
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

    public void LoadMenu1(ArrayList<Object> arrayList, RecyclerView recyclerView) {
        arrayList.add( new Menu(R.drawable.litramen, "Lit Ramen", "This is a descyuription of lit ramen", "1000"));
        arrayList.add( new Menu(R.drawable.litramen, "Lit Ramen", "This is a desclikujyhyription of lit ramen", "1000"));
        arrayList.add( new Menu(R.drawable.litramen, "Lit Ramen", "This is a deuiytscription of lit ramen", "1000"));
        arrayList.add( new Menu(R.drawable.litramen, "Lit Ramen", "This is a de=ramen", "1000"));

        MenuRecyclerAdapter recyclerAdapter = new MenuRecyclerAdapter(arrayList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private View experiment(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding1 = ActivityFullComapnypageViewBinding.inflate(getLayoutInflater());
        setContentView(binding1.getRoot());

        View root = binding1.getRoot();
        determine = 0;

        return root;
    }


}