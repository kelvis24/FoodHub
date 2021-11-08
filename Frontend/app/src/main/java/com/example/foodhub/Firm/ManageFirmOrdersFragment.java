package com.example.foodhub.Firm;

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

/**
 * Controls the R.layout.fragment_manage_firm_orders view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class ManageFirmOrdersFragment extends Fragment {

    private String username;
    private String password;

    private ViewGroup container;

    /**
     * Collects information from bundle where applicable
     * @param savedInstanceState a bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username");
            password = getArguments().getString("password");
        }
    }

    /**
     * Does bookkeeping, including refreshing the page
     * @param inflater A layout inflater
     * @param container The container of this view
     * @param savedInstanceState a bundle passed in
     * @return The created view
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_firm_orders, container, false);
        this.container = container;
        refresh();
        return view;
    }

    /**
     * Makes a call to refresh the page
     */
    public void refresh() {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        Call.post("firms-get-orders", obj, this::listOrders, null);
    }

    /**
     * Lists order information upon a successful call to refresh the page
     * @param arr The response from the server as a JSONArray
     */
    public void listOrders(JSONArray arr) {
        ArrayList<Order> orders = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{Order order = new Order(arr.getJSONObject(i));
                if (order.getStatus() == 0) orders.add(order);
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.manage_firm_orders_recycler);
        recyclerView.setAdapter(new ManageFirmOrdersAdapter(username, password, this, orders));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

}
