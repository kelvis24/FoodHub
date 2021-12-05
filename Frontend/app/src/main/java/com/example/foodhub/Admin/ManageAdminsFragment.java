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

import com.example.foodhub.Common.Admin;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A controller for the R.layout.fragment_manage_admins view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class ManageAdminsFragment extends Fragment {

    private String username;
    private String password;

    private ViewGroup container;

    /**
     * Constructs a ManageAdminsFragment from enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     */
    public ManageAdminsFragment(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    /**
     * A default constructor
     */
    public ManageAdminsFragment() {
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
     * Creates the view, binding the "add admin" button to the proper method
     * @param inflater A layout inflater
     * @param container The view that contains this one
     * @param savedInstanceState A bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_admins, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.add_admin_button1);
        btn.setOnClickListener(this::goToCreateAdmin);
        refresh();
        return view;
    }

    /**
     * Makes a call to the server to get admins, refresh the page
     */
    public void refresh() {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        Call.post("admins-get-admins", obj, this::listAdmins, null);
    }

    /**
     * Builds the list of admins in the recycler upon a successful get-admins method call
     * @param arr The response from the server, as a JSONArray
     */
    public void listAdmins(JSONArray arr) {
        ArrayList<Admin> admins = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{Admin admin = new Admin(arr.getJSONObject(i));
                if (admin.getType() == 0)admins.add(admin);
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.manage_admins_recycler);
        recyclerView.setAdapter(new ManageAdminsAdapter(username, password, this, admins));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    /**
     * Goes to the page to create an admin when the "add admin" button is pressed
     * @param view The "add admin" button
     */
    public void goToCreateAdmin(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.owner_fragment_main, new AddAdminFragment(username, password, "add", 0));
        ft.commit();
    }

}