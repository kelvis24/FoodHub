package com.example.foodhub.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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

/**
 * Sets up the view adapter for Firms
 * @author Marcus Reecy, Arvid Gustafson
 * @see Fragment
 */
public class ViewCategoriesFragment extends Fragment {

    private final long firmId;
    private final String username;
    private final String password;
    private final String type;

    private ViewGroup container;

    /**
     * Constructs a ViewFirmAFragment from enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param firmId The ID of the current firm being used
     */
    public ViewCategoriesFragment(long firmId, String username, String password, String type) {
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    /**
     * Default Constructor
     */
    public ViewCategoriesFragment() {
		this.firmId = 0;
        this.username = null;
        this.password = null;
        this.type = null;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates the view
     * @param inflater
     * @param container
     * @param saveInstanceState
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_categories, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.view_categories_back_button);
        btn.setOnClickListener(this::goToManageFirms);
        refresh();
        return view;
    }

    /**
     * Refreshes the page
     */
    public void refresh() {
        Map<String, String> map = new HashMap<>();
        JSONObject obj = new JSONObject(map);
        try{obj.put("id", firmId);
        } catch (JSONException e) {e.printStackTrace();}
        Call.post("general-get-categories", obj, this::listCategories, null);
    }

    /**
     * Goes through listCategories
     */
    public void listCategories(JSONArray arr) {
        ArrayList<Category> categories = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{categories.add(new Category(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.view_categories_recycler);
        recyclerView.setAdapter(new ViewCategoriesAdapter(firmId, username, password, type, this, categories));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    public void goToManageFirms(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (type.equals("owner"))
            ft.replace(R.id.owner_fragment_main, new ManageFirmsFragment(username, password, type));
        else
            ft.replace(R.id.admin_fragment_main, new ManageFirmsFragment(username, password, type));
        ft.commit();
    }

}
