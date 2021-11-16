package com.example.foodhub.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodhub.Common.Firm;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Controls the R.layout.fragment_manage_firms view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class ManageFirmsFragment extends Fragment {

    private String username;
    private String password;
    private String type;

    private ViewGroup container;

    /**
     * Constructs a ManageFirmsFragment from enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param type The type of the current user: admin or owner
     */
    public ManageFirmsFragment(String username, String password, String type) {
        super();
        this.username = username;
        this.password = password;
        this.type = type;
    }

    /**
     * A default constructor
     */
    public ManageFirmsFragment() {
        this.username = null;
        this.password = null;
        this.type = null;
    }

    /**
     * Collects information from bundle where applicable
     * @param savedInstanceState a bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username");
            password = getArguments().getString("password");
            type = getArguments().getString("type");
        }
    }

    /**
     * Binds the "add firm" button to its proper method when view is created
     * @param inflater A layout inflater
     * @param container The container of this view
     * @param savedInstanceState a bundle passed in
     * @return The created view
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_firms, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.add_firm_button1);
        btn.setOnClickListener(this::goToCreateFirm);
        refresh();
        return view;
    }

    /**
     * Makes a call to refresh the page
     */
    public void refresh() {
        Call.get("general-get-firms", this::listFirms, null);
    }

    /**
     * Lists firm information upon a successful call to refresh the page
     * @param arr The response from the server as a JSONArray
     */
    public void listFirms(JSONArray arr) {
        ArrayList<Firm> firms = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{firms.add(new Firm(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.manage_firms_recycler);
        recyclerView.setAdapter(new ManageFirmsAdapter(username, password, this, firms));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    /**
     * Correctly navigates to the view to add a firm when the "add firm" button is clicked
     * @param view The "add firm" button
     */
    public void goToCreateFirm(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (type.equals("owner"))
            ft.replace(R.id.owner_fragment_main, new AddFirmFragment(username, password, type));
        else
            ft.replace(R.id.admin_fragment_main, new AddFirmFragment(username, password, type));
        ft.commit();
    }
}