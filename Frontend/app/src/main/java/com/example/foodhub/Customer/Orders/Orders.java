package com.example.foodhub.Customer.Orders;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodhub.Class.Company;
import com.example.foodhub.Class.Menu;
import com.example.foodhub.Customer.Home.HomeViewModel;
import com.example.foodhub.Program.OrderInitializer;
import com.example.foodhub.Program.ProgramController;
import com.example.foodhub.R;
import com.example.foodhub.Recyclers.MenuRecyclerAdapter;
import com.example.foodhub.Recyclers.RecyclerAdapter;
import com.example.foodhub.databinding.FragmentHomeBinding;
import com.example.foodhub.databinding.FragmentOrdersBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Orders#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Orders extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
private FragmentOrdersBinding binding;
    private OrdersViewModel orderViewModel;

    public Orders() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Orders.
     */
    // TODO: Rename and change types and number of parameters
    public static Orders newInstance(String param1, String param2) {
        Orders fragment = new Orders();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        orderViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);

        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.recyclerView2;

        // ArrayList<Object> array = new ArrayList<>();
        //array = (  ArrayList<Object>) homeViewModel.getCompanies();


//        MenuRecyclerAdapter recyclerAdapter = new MenuRecyclerAdapter( orderViewModel.getOrders());
        MenuRecyclerAdapter recyclerAdapter = new MenuRecyclerAdapter( getObject());
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
//        }
//        else {
//            return experiment(inflater, container, savedInstanceState);
//
//        }

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_orders, container, false);
    }


    private ArrayList<Object> getObject() {
        ArrayList<Object> arrayList = new ArrayList<>();
        //arrayList.add(getlocalArray().get(0));

        for(int i = 0; i < ProgramController.MenuOrders.size(); ++i ) {
            arrayList.add(getDynamicArray().get(i));
        }

        return arrayList;
    }

    public static ArrayList<Menu> getlocalArray() {
        ArrayList<Menu> company = new ArrayList<>();
        company.add( new Menu(R.drawable.litramen, "this is a description", "userame", "6543"));
        return company;
    }

    public static ArrayList<Menu> getDynamicArray() {
      //  ProgramController.MenuOrders.add(new Menu(R.drawable.wasabilogo, "death vallies", "usme", "653"));
        return ProgramController.MenuOrders;
    }
}