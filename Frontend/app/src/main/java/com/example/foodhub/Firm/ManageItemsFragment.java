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

import com.example.foodhub.Common.Item;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controls the R.layout.fragment_manage_items view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class ManageItemsFragment extends Fragment {

    private long firmId;
    private long categoryId;
    private String username;
    private String password;

    private ViewGroup container;

    /**
     * Constructs a ManageItemsFragment from enumerated information
     * @param firmId The id of the firm of the items here
     * @param categoryId The id of the category of the items here
     * @param username The username of the current user
     * @param password The password of the current user
     */
    public ManageItemsFragment(long firmId, long categoryId, String username, String password) {
        this.firmId = firmId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
    }

    /**
     * A default constructor
     */
    public ManageItemsFragment() {
        this.username = null;
        this.password = null;
    }

    /**
     * Collects information from bundle where applicable
     * @param savedInstanceState a bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Binds the "add item" and "back" buttons to their proper methods when view is created
     * @param inflater A layout inflater
     * @param container The container of this view
     * @param savedInstanceState a bundle passed in
     * @return The created view
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_items, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.add_item_button1);
        btn.setOnClickListener(this::goToCreateItem);
        btn = view.findViewById(R.id.manage_items_back_button);
        btn.setOnClickListener(this::goToManageCategories);
        refresh();
        return view;
    }

    /**
     * Makes a call to refresh the page
     */
    public void refresh() {
        Map<String, String> map = new HashMap<>();
        JSONObject obj = new JSONObject(map);
        try{obj.put("id", categoryId);
        } catch (JSONException e) {e.printStackTrace();}
        Call.post("general-get-items", obj, this::listItems, null);
    }

    /**
     * Lists items information upon a successful call to refresh the page
     * @param arr The response from the server as a JSONArray
     */
    public void listItems(JSONArray arr) {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{items.add(new Item(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.manage_items_recycler);
        recyclerView.setAdapter(new ManageItemsAdapter(firmId, categoryId, username, password, this, items));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    /**
     * Navigates to the view to add an item when the "add item" button is clicked
     * @param view The "add item" button
     */
    public void goToCreateItem(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.firm_fragment_main, new AddItemFragment(firmId, categoryId, username, password));
        ft.commit();
    }

    /**
     * Navigates back to the add category view when the "back" button is clicked
     * @param view The "back" button
     */
    public void goToManageCategories(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.firm_fragment_main, new ManageCategoriesFragment(firmId, username, password));
        ft.commit();
    }

}
