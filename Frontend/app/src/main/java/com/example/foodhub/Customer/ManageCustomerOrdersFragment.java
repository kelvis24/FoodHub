package com.example.foodhub.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

/**
 * Controls the R.layout.fragment_manage_customer_orders view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class ManageCustomerOrdersFragment extends Fragment {

    private String username;
    private String password;
    private ViewGroup container;
    ArrayList<Firm> Firms;

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
        View view = inflater.inflate(R.layout.fragment_manage_customer_orders, container, false);
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
        Call.get("general-get-firms", this::listFirms, null);
        Call.post("customers-get-orders", obj, this::listOrders, null);
    }

    /**
     * Lists order information upon a successful call to refresh the page
     * @param arr The response from the server as a JSONArray
     */
    public void listOrders(JSONArray arr) {
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Firm> firms = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{orders.add(new Order(arr.getJSONObject(i)));

             //   Firms.get
            //    firms.add(new Firm((String)arr.getJSONObject(i).get("firm")));
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.manage_customer_orders_recycler);
        recyclerView.setAdapter(new ManageCustomerOrdersAdapter(username, password, this, orders));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    public void listFirms(JSONArray arr) {
        Firms = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{Firms.add(new Firm(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
//        RecyclerView recyclerView = container.findViewById(R.id.browse_firms_recycler);
//        recyclerView.setAdapter(new BrowseFirmsAdapter(username, password, this, firms));
//        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

}
