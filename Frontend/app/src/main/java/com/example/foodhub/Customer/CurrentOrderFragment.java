package com.example.foodhub.Customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.ItemReference;
import com.example.foodhub.Common.ItemReferenceAdapter;
import com.example.foodhub.Firm.ManageCategoriesFragment;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CurrentOrderFragment extends Fragment {

    private long firmId;
    private long categoryId;
    private String username;
    private String password;
    private ArrayList<ItemReference> order;
    private String from;

    private ViewGroup container;

    public CurrentOrderFragment(long firmId, long categoryId, String username, String password,
            ArrayList<ItemReference> order, String from) {
        this.firmId = firmId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.order = order;
        this.from = from;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_order, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.current_order_back_button);
        btn.setOnClickListener(this::goBack);
        btn = view.findViewById(R.id.current_order_submit_button);
        btn.setOnClickListener(this::submitRequest);
        RecyclerView recyclerView = view.findViewById(R.id.current_order_recycler);
        recyclerView.setAdapter(new ItemReferenceAdapter(this, order));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        return view;
    }

    public void goBack(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (from.equals("BrowseItems"))
            ft.replace(R.id.customer_fragment_main, new BrowseItemsFragment(firmId, categoryId, username, password, order));
        else
            ft.replace(R.id.customer_fragment_main, new BrowseCategoriesFragment(firmId, username, password, order));
        ft.commit();
    }

    public void submitRequest(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        try{JSONArray arr = new JSONArray();
            for(int i = 0; i < order.size(); i++) {
                Map<String, String> notesMap = new HashMap();
                notesMap.put("notes", order.get(i).getNotes());
                JSONObject itemObj = new JSONObject(map);
                itemObj.put("itemId", order.get(i).getItem().getId());
                itemObj.put("quantity", order.get(i).getQuantity());
                arr.put(itemObj);
            }
            JSONObject dataObj = new JSONObject();
            dataObj.put("firmId", firmId);
            dataObj.put("orderList", arr);
            obj.put("data", dataObj);
        } catch (JSONException e) {e.printStackTrace();}
        Call.post("customers-create-order", obj, this::submitResponse, null);
    }

    public void submitResponse(JSONObject response) {
        System.out.println(response.toString());
        try{if (response.get("message").equals("success")) {
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new BrowseFirmsFragment(username, password));
            ft.commit();
        }} catch (Exception e) {Log.d("response", e.toString());}
    }

}
