package com.example.foodhub.Firm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodhub.Common.Item;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Controls the R.layout.fragment_add_item view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class AddItemFragment extends Fragment {

    private final long firmId;
    private final long categoryId;
    private final String username;
    private final String password;
    private final Item item;

    View page;

    /**
     * Constructs a new AddItemFragment given enumerated information
     * @param firmId The id of the firm to which the item belongs
     * @param categoryId The id of the category to which the item belongs
     * @param username The username of the current user
     * @param password The password of the current user
     */
    public AddItemFragment(long firmId, long categoryId, String username, String password, Item item) {
        super();
        this.firmId = firmId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.item = item;
    }

    /**
     * Constructs a new AddItemFragment given enumerated information
     * @param firmId The id of the firm to which the item belongs
     * @param categoryId The id of the category to which the item belongs
     * @param username The username of the current user
     * @param password The password of the current user
     */
    public AddItemFragment(long firmId, long categoryId, String username, String password) {
        super();
        this.firmId = firmId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.item = null;
    }

    /**
     * A default constructor
     */
    public AddItemFragment() {
        this.firmId = -1;
        this.categoryId = -1;
        this.username = null;
        this.password = null;
        this.item = null;
    }

    /**
     * Does bookkeeping for the onCreate method
     * @param savedInstanceState a bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Binds the "add item" button to appropriate function when the view is created
     * @param inflater A layout inflater
     * @param container The view that contains this one
     * @param savedInstanceState a bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        page = inflater.inflate(R.layout.fragment_add_item, container, false);
        Button btn = page.findViewById(R.id.add_item_button2);
        btn.setOnClickListener(this::addItemRequest);
        if (item != null) {
            String str;
            btn.setText(R.string.Edit);
            ((EditText)page.findViewById(R.id.add_item_title)).setText(item.getTitle());
            ((EditText)page.findViewById(R.id.add_item_description)).setText(item.getDescription());
            str = "" + item.getPrice();
            ((EditText)page.findViewById(R.id.add_item_price)).setText(str);
        }
        return page;
    }

    /**
     * Sends a request to add an item upon clicking the "add item" button
     * @param view the "add item" button
     */
    public void addItemRequest(View view) {
        String d_title = ((EditText)page.findViewById(R.id.add_item_title)).getText().toString();
        String d_description = ((EditText)page.findViewById(R.id.add_item_description)).getText().toString();
        double d_price = Double.parseDouble(((EditText)page.findViewById(R.id.add_item_price)).getText().toString());
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("title", d_title);
        dataMap.put("description", d_description);
        JSONObject dataObj = new JSONObject(dataMap);
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        try{dataObj.put("price", d_price);
            if (item == null)
                obj.put("categoryId", categoryId);
            else
                obj.put("itemId", item.getId());
            obj.put("data", dataObj);
        } catch (JSONException e) {e.printStackTrace();}
        if (item == null)
            Call.post("firms-create-item", obj, this::addItemResponse, null);
        else
            Call.post("firms-edit-item", obj, this::addItemResponse, null);
    }

    /**
     * Goes back to the R.layout.fragment_manage_items view upon successfully adding an item
     * @param response The response from the server as a JSONObject
     */
    public void addItemResponse(JSONObject response) {
        try{if (response.get("message").equals("success")) {
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.firm_fragment_main, new ManageItemsFragment(firmId, categoryId, username, password));
            ft.commit();
        }} catch (Exception e) {Log.d("response", e.toString());}
    }

}
