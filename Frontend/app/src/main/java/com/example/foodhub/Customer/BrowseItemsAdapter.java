package com.example.foodhub.Customer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Item;
import com.example.foodhub.Common.ItemReference;
import com.example.foodhub.R;

import java.util.ArrayList;
import java.util.Locale;
/**
 * Class responsible for returning the view
 */
public class BrowseItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long categoryId;
    private String username;
    private String password;
    private ArrayList<ItemReference> order;
    private BrowseItemsFragment fragment;
    private ArrayList<Item> items;

    /**
     * Method responsible for initializing
     */
    public BrowseItemsAdapter(long categoryId, String username, String password, ArrayList<ItemReference> order,
                              BrowseItemsFragment fragment, ArrayList<Item> items) {
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.order = order;
        this.fragment = fragment;
        this.items = items;
    }

    /**
     * Method responsible for returning the view
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_browse_item, parent, false);
        return new ItemHolder(view);
    }

    /**
     * Method responsible for Binding Views
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.usernameText.setText(items.get(index).getTitle());
        itemHolder.price.setText(String.format(Locale.ENGLISH, "$%.2f", items.get(index).getPrice()));
        AddItem addItem = new AddItem(items.get(index), fragment);
        itemHolder.addButton.setOnClickListener(addItem);
    }

    /**
     * Method responsible for returning the type
     */
    @Override public int getItemViewType(int index) {
        return items.get(index) == null ? -1 : 0;
    }

    /**
     * Method responsible for returning the size
     */
    @Override public int getItemCount() {
        return items.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        TextView price;
        Button addButton;
        public ItemHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.browse_item_textview);
            price = view.findViewById(R.id.browse_item_price);
            addButton = view.findViewById(R.id.browse_item_button);
        }
    }

    private class AddItem implements View.OnClickListener {
        private Item item;
        private BrowseItemsFragment fragment;

        public AddItem(Item item, BrowseItemsFragment fragment) {
            this.item = item;
            this.fragment = fragment;
        }

        public void onClick(View v) {
            order.add(new ItemReference(item, 1, ""));
        }

    }

}
