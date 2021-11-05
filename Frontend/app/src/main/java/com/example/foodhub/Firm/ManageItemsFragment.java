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

public class ManageItemsFragment extends Fragment {

    private long firmId;
    private long categoryId;
    private String username;
    private String password;

    private ViewGroup container;

    public ManageItemsFragment(long firmId, long categoryId, String username, String password) {
        this.firmId = firmId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
    }

    public ManageItemsFragment() {
        this.username = null;
        this.password = null;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

    public void refresh() {
        Map<String, String> map = new HashMap<>();
        JSONObject obj = new JSONObject(map);
        try{obj.put("id", categoryId);
        } catch (JSONException e) {e.printStackTrace();}
        Call.post("general-get-items", obj, this::listItems, null);
    }

    public void listItems(JSONArray arr) {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{items.add(new Item(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        RecyclerView recyclerView = container.findViewById(R.id.manage_items_recycler);
        recyclerView.setAdapter(new ManageItemsAdapter(categoryId, username, password, this, items));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    public void goToCreateItem(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.firm_fragment_main, new AddItemFragment(firmId, categoryId, username, password));
        ft.commit();
    }

    public void goToManageCategories(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.firm_fragment_main, new ManageCategoriesFragment(firmId, username, password));
        ft.commit();
    }

}
