package com.example.foodhub.Customer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
    ArrayList<Firm> myFirms;
    ArrayList<Order> Orders;

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

        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);

//        findViewById(R.id.button)
//                .setOnClickListener(v -> {
//
//                    Intent intent = new Intent(this, ChatActivity.class);
//                    intent.putExtra("name", username);
//                    startActivity(intent);
//
//                });


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

        view.findViewById(R.id.button).setOnClickListener(this::clid);
        return view;
    }


    public void clid(View v) {
        final FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.replace(R.id.customer_fragment_main, new OrderChatFragment("Taco House", username, password));
        ft.commit();
    }

    /**
     * Makes a call to refresh the page
     */
    public void refresh() {
        Call.get("general-get-firms", this::listFirms, null);
    }

    /**
     * Lists order information upon a successful call to refresh the page
     * @param arr The response from the server as a JSONArray
     */
    public void listOrders(JSONArray arr) {
        Orders = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{Orders.add(new Order(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.manage_customer_orders_recycler);
        recyclerView.setAdapter(new BrowseMyFirmsAdapter(username, password, this, Firm.getListOfFirmsWithMyOrders(Firms, Orders)));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    public void listFirms(JSONArray arr) {
        Firms = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{Firms.add(new Firm(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        Call.post("customers-get-orders", obj, this::listOrders, null);
    }
}
