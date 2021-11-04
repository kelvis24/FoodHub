package com.example.foodhub.Admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddAdminFragment extends Fragment {

    private final String username;
    private final String password;

    private ViewGroup container;
    View view;

    public AddAdminFragment(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public AddAdminFragment() {
        this.username = null;
        this.password = null;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_admin, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.add_admin_button2);
        btn.setOnClickListener(this::addAdminRequest);
        return view;
    }

    public void addAdminRequest(View v) {
        String d_name = ((EditText)view.findViewById(R.id.add_admin_name)).getText().toString();
        String d_username = ((EditText)view.findViewById(R.id.add_admin_username)).getText().toString();
        String d_password = ((EditText)view.findViewById(R.id.add_admin_password)).getText().toString();
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("name", d_name);
        dataMap.put("username", d_username);
        dataMap.put("password", d_password);
        JSONObject dataObj = new JSONObject(dataMap);
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        try{obj.put("data", dataObj);
        } catch (JSONException e) {e.printStackTrace();}
        Call.post("admins-create-admin", obj, this::addAdminResponse, null);
    }

    public void addAdminResponse(JSONObject response) {
        try{if (response.get("message").equals("success")) {
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.owner_fragment_main, new ManageAdminsFragment(username, password));
            ft.commit();
        }} catch (Exception e) {Log.d("response", e.toString());}
    }

}
