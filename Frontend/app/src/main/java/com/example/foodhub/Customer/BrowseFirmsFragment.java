package com.example.foodhub.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Firm;
import com.example.foodhub.Firm.ManageCategoriesAdapter;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for the Browse Firms Fragment
 */
public class BrowseFirmsFragment extends Fragment {

    private String username;
    private String password;
    private ViewGroup container;

    /**
     * Method responsible for initializing
     */
    public BrowseFirmsFragment(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Another Method responsible for initializing
     */
    public BrowseFirmsFragment() {
        this.username = null;
        this.password = null;
    }

    /**
     * Method responsible for the onCreate
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username");
            password = getArguments().getString("password");
        }
    }

    /**
     * Method responsible foronCreateView
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse_firms, container, false);
        this.container = container;
        refresh();
        return view;
    }

    /**
     * Method responsible for refreshing
     */
    public void refresh() {
        Call.get("general-get-firms", this::listFirms, null);
    }

    /**
     * Method responsible for using the JSON array
     */
    public void listFirms(JSONArray arr) {
        ArrayList<Firm> firms = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{firms.add(new Firm(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.browse_firms_recycler);
        recyclerView.setAdapter(new BrowseFirmsAdapter(username, password, this, firms));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

}
