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

public class ManageItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long categoryId;
    private String username;
    private String password;

    private ManageItemsFragment fragment;

    private ArrayList<Item> items;

    public ManageItemsAdapter(long categoryId, String username, String password,
            ManageItemsFragment fragment, ArrayList<Item> items) {
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.items = items;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_edit_item, parent, false);
        return new ItemHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.usernameText.setText(items.get(index).getTitle());
        DeleteItem deleteItem = new DeleteItem(items.get(index).getId(), fragment);
        itemHolder.deleteButton.setOnClickListener(deleteItem);
    }

    @Override public int getItemViewType(int index) {
        return items.get(index) == null ? -1 : 0;
    }

    @Override public int getItemCount() {
        return items.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        Button deleteButton;
        public ItemHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.edit_item_textview);
            deleteButton = view.findViewById(R.id.edit_item_button);
        }
    }

    class DeleteItem implements View.OnClickListener, ObjectResponse {
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
