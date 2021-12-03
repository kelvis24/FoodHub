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

/*

    @author Marcus
 */


public class ViewFirmFragment extends Fragment {

    private long firmId;
    private String username;
    private String password;

    private ViewGroup container;

    public ViewFirmFragment(long firmId, String username, String password) {
        this.firmId = firmId;
        this.username = username;
        this.password = password;
    }

    public ViewFirmFragment() {
        this.username = null;
        this.password = null;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse_categories, container, false);
        this.container = container;
        Button btn = view.findViewById(R.id.browse_categories_view_order_button);
        btn.setOnClickListener(this::goToViewOrder);
        btn = view.findViewById(R.id.browse_categories_back_button);
        btn.setOnClickListener(this::goToBrowseFirms);
        refresh();
        return view;
    }

    public void refresh() {
        Map<String, String> map = new HashMap<>();
        JSONObject obj = new JSONObject(map);
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
        RecyclerView recyclerView = container.findViewById(R.id.browse_categories_recycler);
        recyclerView.setAdapter(new ViewFirmAdapter(firmId, username, password, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

    public void goToViewOrder(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.customer_fragment_main, new ViewFirmFragment(firmId, username, password));
        ft.commit();
    }

    public void goToBrowseFirms(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.customer_fragment_main, new ViewFirmFragment(firmId, username, password));
        ft.commit();
    }

}
