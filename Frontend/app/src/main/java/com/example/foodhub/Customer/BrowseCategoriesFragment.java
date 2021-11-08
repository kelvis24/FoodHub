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

import com.example.foodhub.Common.Category;
import com.example.foodhub.Common.ItemReference;
import com.example.foodhub.Firm.AddCategoryFragment;
import com.example.foodhub.Firm.ManageCategoriesAdapter;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A controller for the R.layout.fragment_browse_categories view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class BrowseCategoriesFragment extends Fragment {

    private long firmId;
    private String username;
    private String password;
    private ArrayList<ItemReference> order;
    private ViewGroup container;

    /**
     * Constructs a BrowseCategoriesFragment from enumerated information
     * @param firmId The id of the firm of the categories being browsed
     * @param username The username of the current user
     * @param password The password of the current user
     * @param order The qualities of the ongoing order
     */
    public BrowseCategoriesFragment(long firmId, String username, String password, ArrayList<ItemReference> order) {
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.order = order;
    }

    /**
     * A default constructor
     */
    public BrowseCategoriesFragment() {
        this.username = null;
        this.password = null;
    }

    /**
     * Does bookkeeping related to the onCreate method
     * @param savedInstanceState A bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates the view, binding the buttons to the proper methods
     * @param inflater A layout inflater
     * @param container The view that contains this one
     * @param savedInstanceState A bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse_categories, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.browse_categories_view_order_button);
        btn.setOnClickListener(this::goToViewOrder);
        btn = view.findViewById(R.id.browse_categories_back_button);
        btn.setOnClickListener(this::goToBrowseFirms);
        refresh();
        return view;
    }

    /**
     * Makes a call to the server to get categories, and refresh the page
     */
    public void refresh() {
        Map<String, String> map = new HashMap<>();
        JSONObject obj = new JSONObject(map);
        try{obj.put("id", firmId);
        } catch (JSONException e) {e.printStackTrace();}
        Call.post("general-get-categories", obj, this::listCategories, null);
    }

    /**
     * Builds the list of categories in the recycler upon a successful get-categories method call
     * @param arr The response from the server, as a JSONArray
     */
    public void listCategories(JSONArray arr) {
        ArrayList<Category> categories = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{categories.add(new Category(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.browse_categories_recycler);
        recyclerView.setAdapter(new BrowseCategoriesAdapter(firmId, username, password, order, this, categories));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    /**
     * Goes to the page to view and confirm an order when the "view order" button is pressed
     * @param view The "view order" button
     */
    public void goToViewOrder(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.customer_fragment_main, new CurrentOrderFragment(firmId, -1, username, password, order, "BrowseCategories"));
        ft.commit();
    }

    /**
     * Goes to the page to browse firms when the "back" button is pressed
     * @param view The "back" button
     */
    public void goToBrowseFirms(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.customer_fragment_main, new BrowseFirmsFragment(username, password));
        ft.commit();
    }

}
