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

public class AddFirmFragment extends Fragment {

    private final String username;
    private final String password;

    private ViewGroup container;
    View view;

    public AddFirmFragment(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public AddFirmFragment() {
        this.username = null;
        this.password = null;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_firm, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.add_firm_button2);
        btn.setOnClickListener(this::addFirmRequest);
        return view;
    }

    public void addFirmRequest(View v) {
        String d_name = ((EditText)view.findViewById(R.id.add_firm_name)).getText().toString();
        String d_username = ((EditText)view.findViewById(R.id.add_firm_username)).getText().toString();
        String d_password = ((EditText)view.findViewById(R.id.add_firm_password)).getText().toString();
        String d_location = ((EditText)view.findViewById(R.id.add_firm_location)).getText().toString();
        String d_cuisine = ((EditText)view.findViewById(R.id.add_firm_cuisine)).getText().toString();
        int d_open_time = Integer.parseInt(((EditText)view.findViewById(R.id.add_firm_open_time)).getText().toString());
        int d_close_time = Integer.parseInt(((EditText)view.findViewById(R.id.add_firm_close_time)).getText().toString());
        int d_employee_count= Integer.parseInt(((EditText)view.findViewById(R.id.add_firm_employee_count)).getText().toString());
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("name", d_name);
        dataMap.put("username", d_username);
        dataMap.put("password", d_password);
        dataMap.put("location", d_location);
        dataMap.put("cuisine", d_cuisine);
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject dataObj = new JSONObject(dataMap);
        JSONObject obj = new JSONObject(map);
        try{dataObj.put("open_time", d_open_time);
            dataObj.put("close_time", d_close_time);
            dataObj.put("employee_count", d_employee_count);
            obj.put("data", dataObj);
        } catch (JSONException e) {e.printStackTrace();}
        Call.post("admins-create-firm", obj, this::addFirmResponse, null);
    }

    public void addFirmResponse(JSONObject response) {
        try{if (response.get("message").equals("success")) {
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.owner_fragment_main, new ManageFirmsFragment(username, password));
            ft.commit();
        }} catch (Exception e) {Log.d("response", e.toString());}
    }

}
