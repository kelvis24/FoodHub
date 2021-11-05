package com.example.foodhub.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Order;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManageCustomerOrdersFragment extends Fragment {

    private String username;
    private String password;

    private ViewGroup container;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username");
            password = getArguments().getString("password");
        }
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_customer_orders, container, false);
        this.container = container;
        refresh();
        return view;
    }

    public void refresh() {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        Call.post("customers-get-orders", obj, this::listOrders, null);
    }

    public void listOrders(JSONArray arr) {
        ArrayList<Order> orders = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{orders.add(new Order(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.manage_customer_orders_recycler);
        recyclerView.setAdapter(new ManageCustomerOrdersAdapter(username, password, this, orders));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

}
