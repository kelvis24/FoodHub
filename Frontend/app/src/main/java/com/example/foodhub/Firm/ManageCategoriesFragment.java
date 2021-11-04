package com.example.foodhub.Firm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Category;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManageCategoriesFragment extends Fragment {

    private long firmId;
    private String username;
    private String password;

    private ViewGroup container;

    public ManageCategoriesFragment(long firmId, String username, String password) {
        super();
        this.firmId = firmId;
        this.username = username;
        this.password = password;
    }

    public ManageCategoriesFragment() {
        this.username = null;
        this.password = null;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            firmId = getArguments().getLong("firmId");
            username = getArguments().getString("username");
            password = getArguments().getString("password");
        }
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_categories, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.add_category_button1);
        btn.setOnClickListener(this::goToCreateCategory);
        refresh();
        return view;
    }

    public void refresh() {
        JSONObject obj = new JSONObject();
        try{obj.put("id", firmId);
        } catch (JSONException e) {e.printStackTrace();}
        Call.post("general-get-categories", obj, this::listCategories, null);
    }

    public void listCategories(JSONArray arr) {
        ArrayList<Category> categories = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{categories.add(new Category(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.manage_categories_recycler);
        // recyclerView.setAdapter(new ManageAdminsAdapter(id, username, password, this, categories));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    public void goToCreateCategory(View view) {
    }

}
