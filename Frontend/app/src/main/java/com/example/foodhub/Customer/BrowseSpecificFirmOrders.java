package com.example.foodhub.Customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodhub.Common.Firm;
import com.example.foodhub.Common.Order;
import com.example.foodhub.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BrowseSpecificFirmOrders#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BrowseSpecificFirmOrders extends Fragment {

    private String username;
    private String password;
    private long firmId;
    private ViewGroup container;
    ArrayList<Order> orders;

    public BrowseSpecificFirmOrders(long firmId, String username, String password) {

        this.username = username;
        this.password = password;
        this.firmId = firmId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browse_specific_firm_orders, container, false);
    }
}