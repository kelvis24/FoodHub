package com.example.foodhub.Customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodhub.Common.Firm;
import com.example.foodhub.Common.Order;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BrowseSpecificFirmOrders extends Fragment {

    private String username;
    private String password;
    private String firmName;
    private ViewGroup container;
    ArrayList<Order> orders;

    public BrowseSpecificFirmOrders(String firmName, String username, String password) {
        this.username = username;
        this.password = password;
        this.firmName = firmName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse_specific_firm_orders, container, false);
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

    /**
     * Lists order information upon a successful call to refresh the page
     * @param arr The response from the server as a JSONArray
     */
    public void listOrders(JSONArray arr) {
        orders = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{orders.add(new Order(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }


        RecyclerView recyclerView = container.findViewById(R.id.see_specific_orders_to_firm);
        recyclerView.setAdapter(new ManageCustomerOrdersAdapter(username, password, this, Order.returnOrdersSpecificToAFirm(firmName, orders)));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }
}