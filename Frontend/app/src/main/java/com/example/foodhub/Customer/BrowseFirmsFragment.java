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
 * A controller for the R.layout.fragment_browse_firms view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class BrowseFirmsFragment extends Fragment {

    private String username;
    private String password;
    private ViewGroup container;

    /**
     * Constructs a BrowseFirmsFragment from enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     */
    public BrowseFirmsFragment(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * A default constructor
     */
    public BrowseFirmsFragment() {
        this.username = null;
        this.password = null;
    }

    /**
     * Gets information from the passed in bundle when applicable
     * @param savedInstanceState A bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username");
            password = getArguments().getString("password");
        }
    }

    /**
     * Creates the view, and refreshes the page
     * @param inflater A layout inflater
     * @param container The view that contains this one
     * @param savedInstanceState A bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse_firms, container, false);
        this.container = container;
        refresh();
        return view;
    }

    /**
     * Makes a call to the server to get firms, refresh the page
     */
    public void refresh() {
        Call.get("general-get-firms", this::listFirms, null);
    }

    /**
     * Builds the list of firms in the recycler upon a successful get-firms method call
     * @param arr The response from the server, as a JSONArray
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
