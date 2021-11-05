package com.example.foodhub.Firm;

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

public class AddCategoryFragment extends Fragment {

    private final long firmId;
    private final String username;
    private final String password;

    private ViewGroup container;
    View view;

    public AddCategoryFragment(long firmId, String username, String password) {
        super();
        this.firmId = firmId;
        this.username = username;
        this.password = password;
    }

    public AddCategoryFragment() {
        this.firmId = -1;
        this.username = null;
        this.password = null;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_category, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.add_category_button2);
        btn.setOnClickListener(this::addCategoryRequest);
        return view;
    }

    public void addCategoryRequest(View v) {
        String d_title = ((EditText)view.findViewById(R.id.add_category_title)).getText().toString();
        String d_description = ((EditText)view.findViewById(R.id.add_category_description)).getText().toString();
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("title", d_title);
        dataMap.put("description", d_description);
        JSONObject dataObj = new JSONObject(dataMap);
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        try{obj.put("data", dataObj);
        } catch (JSONException e) {e.printStackTrace();}
        Call.post("firms-create-category", obj, this::addCategoryResponse, null);
    }

    public void addCategoryResponse(JSONObject response) {
        try{if (response.get("message").equals("success")) {
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.firm_fragment_main, new ManageCategoriesFragment(firmId, username, password));
            ft.commit();
        }} catch (Exception e) {Log.d("response", e.toString());}
    }

}
