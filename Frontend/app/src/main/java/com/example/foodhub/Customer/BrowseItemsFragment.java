package com.example.foodhub.Customer;

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
import com.example.foodhub.Common.ItemReference;
import com.example.foodhub.Firm.ManageItemsAdapter;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The controller for the R.layout.view_browse_items view, placing them in a recycler
 * @author Arvid Gustafson
 * @see RecyclerView.Adapter
 */
public class BrowseItemsFragment extends Fragment {
    private long firmId;
    private long categoryId;
    private String username;
    private String password;
    private ArrayList<ItemReference> order;
    private ViewGroup container;

    /**
     * Constructs a ManageAdminsAdapter given enumerated information
     * @param firmId The id of the firm of the items herein
     * @param categoryId The id of the category of the items herein
     * @param username The username of the current user
     * @param password The password of the current user
     * @param order The qualities of the order thus far
     */
    public BrowseItemsFragment(long firmId, long categoryId, String username, String password, ArrayList<ItemReference> order) {
        this.firmId = firmId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.order = order;
    }

    /**
     * Gets information from the passed in bundle when applicable
     * @param savedInstanceState A bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates the view, binding the buttons to the proper methods, and refreshes the page
     * @param inflater A layout inflater
     * @param container The view that contains this one
     * @param savedInstanceState A bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse_items, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.browse_items_view_order_button);
        btn.setOnClickListener(this::goToViewOrder);
        btn = view.findViewById(R.id.browse_items_back_button);
        btn.setOnClickListener(this::goToBrowseCategories);
        refresh();
        return view;
    }

    /**
     * Makes a call to the server to get items, refresh the page
     */
    public void refresh() {
        Map<String, String> map = new HashMap<>();
        JSONObject obj = new JSONObject(map);
        try{obj.put("id", categoryId);
        } catch (JSONException e) {e.printStackTrace();}
        Call.post("general-get-items", obj, this::listItems, null);
    }

    /**
     * Builds the list of items in the recycler upon a successful get-items method call
     * @param arr The response from the server, as a JSONArray
     */
    public void listItems(JSONArray arr) {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{items.add(new Item(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.browse_items_recycler);
        recyclerView.setAdapter(new BrowseItemsAdapter(categoryId, username, password, order, this, items));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    /**
     * Navigates to the "view order" page upon clicking the "view order" button
     * @param view The "view order" button
     */
    public void goToViewOrder(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.customer_fragment_main, new CurrentOrderFragment(firmId, categoryId, username, password, order, "BrowseItems"));
        ft.commit();
    }

    /**
     * Navigates to the "browse categories" page upon clicking the "back" button
     * @param view The "back" button
     */
    public void goToBrowseCategories(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.customer_fragment_main, new BrowseCategoriesFragment(firmId, username, password, order));
        ft.commit();
    }
}
