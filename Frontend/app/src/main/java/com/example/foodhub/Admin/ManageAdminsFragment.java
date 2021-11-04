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

public class ManageAdminsFragment extends Fragment {

    private String username;
    private String password;

    private ViewGroup container;

    public ManageAdminsFragment(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public ManageAdminsFragment() {
        this.username = null;
        this.password = null;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username");
            password = getArguments().getString("password");
        }
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_admins, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.add_admin_button1);
        btn.setOnClickListener(this::goToCreateAdmin);
        refresh();
        return view;
    }

    public void refresh() {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        Call.post("admins-get-admins", obj, this::listAdmins, null);
    }

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

    public void goToCreateAdmin(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.owner_fragment_main, new AddAdminFragment(username, password));
        ft.commit();
    }

}