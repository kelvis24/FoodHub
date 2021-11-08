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
 * Class responsible for returning the view
 */
public class BrowseItemsFragment extends Fragment {

    private long firmId;
    private long categoryId;
    private String username;
    private String password;
    private ArrayList<ItemReference> order;
    private ViewGroup container;

    /**
     * Method responsible for returning initializing
     */
    public BrowseItemsFragment(long firmId, long categoryId, String username, String password, ArrayList<ItemReference> order) {
        this.firmId = firmId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.order = order;
    }

    /**
     * Method responsible for returning the onCreate Definition
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Method responsible for returning the view
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
     * Method responsible for returning the refreshing
     */
    public void refresh() {
        Map<String, String> map = new HashMap<>();
        JSONObject obj = new JSONObject(map);
        try{obj.put("id", categoryId);
        } catch (JSONException e) {e.printStackTrace();}
        Call.post("general-get-items", obj, this::listItems, null);
    }

    /**
     * Method responsible for returning the view
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
     * Method responsible for implementing the ViewOrder
     */
    public void goToViewOrder(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.customer_fragment_main, new CurrentOrderFragment(firmId, categoryId, username, password, order, "BrowseItems"));
        ft.commit();
    }

    /**
     * Method responsible for browseCategories
     */
    public void goToBrowseCategories(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.customer_fragment_main, new BrowseCategoriesFragment(firmId, username, password, order));
        ft.commit();
    }
}
