package com.example.foodhub.Firm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Admin.AddAdminFragment;
import com.example.foodhub.Common.Category;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controls the R.layout.fragment_manage_categories view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class ManageCategoriesFragment extends Fragment {

    private long firmId;
    private String username;
    private String password;

    private ViewGroup container;

    /**
     * Constructs a ManageCategoriesFragment from enumerated information
     * @param firmid The id of the firm of the categories herein
     * @param username The username of the current user
     * @param password The password of the current user
     */
    public ManageCategoriesFragment(long firmId, String username, String password) {
        this.firmId = firmId;
        this.username = username;
        this.password = password;
    }

    /**
     * A default constructor
     */
    public ManageCategoriesFragment() {
        this.username = null;
        this.password = null;
    }

    /**
     * Collects information from bundle where applicable
     * @param savedInstanceState a bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            firmId = getArguments().getLong("firmId");
            username = getArguments().getString("username");
            password = getArguments().getString("password");
        }
    }

    /**
     * Binds the "add category" button to its proper method when view is created
     * @param inflater A layout inflater
     * @param container The container of this view
     * @param savedInstanceState a bundle passed in
     * @return The created view
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_categories, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.add_category_button1);
        btn.setOnClickListener(this::goToCreateCategory);
        refresh();
        return view;
    }

    /**
     * Makes a call to refresh the page
     */
    public void refresh() {
        Map<String, String> map = new HashMap<>();
        JSONObject obj = new JSONObject(map);
        try{obj.put("id", firmId);
        } catch (JSONException e) {e.printStackTrace();}
        Call.post("general-get-categories", obj, this::listCategories, null);
    }

    /**
     * Lists categories information upon a successful call to refresh the page
     * @param arr The response from the server as a JSONArray
     */
    public void listCategories(JSONArray arr) {
        ArrayList<Category> categories = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{categories.add(new Category(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.manage_categories_recycler);
        recyclerView.setAdapter(new ManageCategoriesAdapter(firmId, username, password, this, categories));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    /**
     * Correctly navigates to the view to add a category when the "add category" button is clicked
     * @param view The "add category" button
     */
    public void goToCreateCategory(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.firm_fragment_main, new AddCategoryFragment(firmId, username, password));
        ft.commit();
    }

}
