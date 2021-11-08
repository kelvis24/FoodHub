package com.example.foodhub.Firm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Item;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.ObjectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class for controlling the R.layout.view_edit_item view, placing it in a recycler
 * @author Arvid Gustafson
 * @see RecyclerView.Adapter
 */
public class ManageItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long categoryId;
    private String username;
    private String password;

    private ManageItemsFragment fragment;

    private ArrayList<Item> items;

    /**
     * Constructs a ManageItemsAdapter object given enumerated information
     * @param categoryId The category of the items here
     * @param username The username of the current user
     * @param password The password of the current user
     * @param fragment The fragment that contains the recycler
     * @param items The list information about items, which will be listed in the recycler
     */
    public ManageItemsAdapter(long categoryId, String username, String password,
            ManageItemsFragment fragment, ArrayList<Item> items) {
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.items = items;
    }

    /**
     * Creates a ViewHolder given a view
     * @param parent The parent view of the recycler
     * @param viewType The type of view; it should always be 0
     * @return The ViewHolder that is created
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_edit_item, parent, false);
        return new ItemHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; it sets the text and binds the button to the right method
     * @param holder The ViewHolder about to be bound
     * @param index The index of the information of ViewHolder in the items array
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.usernameText.setText(items.get(index).getTitle());
        DeleteItem deleteItem = new DeleteItem(items.get(index).getId(), fragment);
        itemHolder.deleteButton.setOnClickListener(deleteItem);
    }

    /**
     * Retrieves the type of view given its index in the items array
     * @param index The index of the view in question
     * @return The type of view; it should always be 0
     */
    @Override public int getItemViewType(int index) {
        return items.get(index) == null ? -1 : 0;
    }

    /**
     * Retrieves the number of views to be in the recycler
     * @return The number of views to be in the recycler
     */
    @Override public int getItemCount() {
        return items.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        Button deleteButton;
        public ItemHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.edit_item_textview);
            deleteButton = view.findViewById(R.id.edit_item_button);
        }
    }

    private class DeleteItem implements View.OnClickListener, ObjectResponse {
        private long itemId;
        private ManageItemsFragment fragment;
        public DeleteItem(long itemId, ManageItemsFragment fragment) {
            this.itemId = itemId;
            this.fragment = fragment;
        }
        public void onClick(View v) {
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            JSONObject obj = new JSONObject(map);
            try{obj.put("id", itemId);
            } catch (JSONException e) {e.printStackTrace();}
            Call.post("firms-remove-item", obj, this, null);
        }
        public void respond(JSONObject response) {
            try{System.out.println(response.toString());
                if (response.get("message").equals("success")) {
                    fragment.refresh();
            }} catch (Exception e) {Log.d("response", e.toString());}
        }
    }

}
