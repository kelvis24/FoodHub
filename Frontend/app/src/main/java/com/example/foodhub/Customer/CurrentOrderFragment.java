package com.example.foodhub.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.ItemReference;
import com.example.foodhub.Common.ItemReferenceAdapter;
import com.example.foodhub.R;

import java.util.ArrayList;

public class CurrentOrderFragment extends Fragment {

    private long firmId;
    private long categoryId;
    private String username;
    private String password;
    private ArrayList<ItemReference> order;
    private String from;

    private ViewGroup container;

    public CurrentOrderFragment(long firmId, long categoryId, String username, String password,
            ArrayList<ItemReference> order, String from) {
        this.firmId = firmId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.order = order;
        this.from = from;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_order, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.current_order_back_button);
        btn.setOnClickListener(this::goBack);
        RecyclerView recyclerView = view.findViewById(R.id.current_order_recycler);
        recyclerView.setAdapter(new ItemReferenceAdapter(this, order));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        return view;
    }

    public void goBack(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (from.equals("BrowseItems"))
            ft.replace(R.id.customer_fragment_main, new BrowseItemsFragment(firmId, categoryId, username, password, order));
        else
            ft.replace(R.id.customer_fragment_main, new BrowseCategoriesFragment(firmId, username, password, order));
        ft.commit();
    }

}
