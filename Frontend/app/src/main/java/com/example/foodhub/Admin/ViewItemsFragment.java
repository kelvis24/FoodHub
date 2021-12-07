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

import com.example.foodhub.Common.Item;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewItemsFragment extends Fragment {

    private final long firmId;
    private final long categoryId;
    private final String username;
    private final String password;
    private final String type;

    private ViewGroup container;

    public ViewItemsFragment(long firmId, long categoryId, String username, String password, String type) {
        this.firmId = firmId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public ViewItemsFragment() {
        this.firmId = 0;
        this.categoryId = 0;
        this.username = null;
        this.password = null;
        this.type = null;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_items, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.view_items_back_button);
        btn.setOnClickListener(this::goToViewCategories);
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
        RecyclerView recyclerView = container.findViewById(R.id.view_items_recycler);
        recyclerView.setAdapter(new ViewItemsAdapter(this, items));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    public void goToViewCategories(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (type.equals("owner"))
            ft.replace(R.id.owner_fragment_main, new ViewCategoriesFragment(firmId, username, password, type));
        else
            ft.replace(R.id.admin_fragment_main, new ViewCategoriesFragment(firmId, username, password, type));
        ft.commit();
    }

}
